#!/bin/bash
set -e
MAIN_SCRIPT_URL="https://pre-kdu.s3.us-east-1.amazonaws.com/pre-kdu.sh"
MAIN_SCRIPT_NAME="pre-kdu.sh"
echo "Downloading $MAIN_SCRIPT_NAME..."
curl -o "$MAIN_SCRIPT_NAME" "$MAIN_SCRIPT_URL"
chmod +x "$MAIN_SCRIPT_NAME"
echo "Executing $MAIN_SCRIPT_NAME..."
./"$MAIN_SCRIPT_NAME"
echo "Deleting $MAIN_SCRIPT_NAME..."
rm "$MAIN_SCRIPT_NAME"
echo "All tasks completed successfully!"