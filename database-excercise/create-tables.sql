CREATE database employee;
USE employee;

create table department (
    department_id int PRIMARY KEY,
    department_name varchar(50)
);
create table employee (
    employee_id int PRIMARY KEY,
    employee_name varchar(50),
    salary varchar(10),
    department_id int ,
    foreign key (department_id) REFERENCES department(department_id)
);
-- insert dummy data 
INSERT INTO department (department_id, department_name) VALUES
(1, 'Human Resources'),
(2, 'Finance'),
(3, 'Engineering'),
(4, 'Marketing');

INSERT INTO employee (employee_id, employee_name, salary, department_id) VALUES
(101, 'Abhishek', 60000.00, 1),
(102, 'Divyata', 75000.00, 2),
(103, 'Dev', 80000.00, 3),
(104, 'Prince', 85000.00, 3),
(105, 'Ragini', 50000.00, 4),
(106, 'Frank', 70000.00, 2);