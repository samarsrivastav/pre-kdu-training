select AVG(salary) as AVERAGE_SALARY,d.department_name from employee e 
join department d on e.department_id=d.department_id 
group by department_id;