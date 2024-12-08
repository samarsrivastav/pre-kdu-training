#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Variables
ASSIGNMENTS_URL="https://pre-kdu.s3.us-east-1.amazonaws.com/assignments.zip"
PRE_COMMIT_URL="https://pre-kdu.s3.us-east-1.amazonaws.com/pre-commit"
COMMIT_MSG_URL="https://pre-kdu.s3.us-east-1.amazonaws.com/commit-msg"
SCRIPTS_URL="https://pre-kdu.s3.us-east-1.amazonaws.com/scripts.zip"
GIT_HOOKS_DIR=".git/hooks"
GIT_SCRIPTS_DIR=".git"

# Download the assignments.zip
echo "Downloading assignments.zip..."
curl -o "assignments.zip" "$ASSIGNMENTS_URL"

# Unzip assignments.zip and place contents at the same level
echo "Unzipping assignments.zip..."
unzip -o "assignments.zip" && rm "assignments.zip"

# Add, commit the changes to Git
echo "Committing changes to Git..."
git add .
git commit -m "Repo initialisation done for pre-kdu"

# Ensure .git/hooks exists
echo "Setting up Git hooks..."
mkdir -p "$GIT_HOOKS_DIR"

# Download pre-commit and commit-msg files and move them to .git/hooks
echo "Downloading Git hook files..."
curl -o "$GIT_HOOKS_DIR/pre-commit" "$PRE_COMMIT_URL"
curl -o "$GIT_HOOKS_DIR/commit-msg" "$COMMIT_MSG_URL"

# Make the hook files executable
chmod +x "$GIT_HOOKS_DIR/pre-commit" "$GIT_HOOKS_DIR/commit-msg"

# Download scripts.zip and move it to the .git/scripts folder
echo "Downloading and organizing scripts.zip..."
mkdir -p "$GIT_SCRIPTS_DIR"
curl -o "$GIT_SCRIPTS_DIR/scripts.zip" "$SCRIPTS_URL"
unzip -o "$GIT_SCRIPTS_DIR/scripts.zip" -d "$GIT_SCRIPTS_DIR" && rm "$GIT_SCRIPTS_DIR/scripts.zip"

# Make all files inside the .git/scripts folder executable
echo "Making all files in $GIT_SCRIPTS_DIR executable..."
chmod +x "$GIT_SCRIPTS_DIR"/scripts/*

echo "All tasks completed successfully!"
