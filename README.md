
Call example: [http://localhost:9090/shifts?ids=1,2,3&user_ids=1,2](http://localhost:9090/shifts?ids=1,2,3&user_ids=1,2)

### etl app

The application hits the rest endpoint application and the accepted json file transforms and stores the values ​​in a mysql database

Call example: [http://localhost:9091/run?ids=1,2,3&user_ids=1,2](http://localhost:9091/run?ids=1,2,3&user_ids=1,2)

## Requirements

Instaled docker.

## Deployment

The root folder contains the defined docker-compose.yml. In root folder following command should be executed:

```bash
docker-compose up
```

## Usage

After the **docker-compose up** command is executed, the **mysql** database will be started with the created **work_shifts** database. 

Once when [http://localhost:9091/run?ids=1,2,3&user_ids=1,2](http://localhost:9091/run?ids=1,2,3&user_ids=1,2) is hited, Json values generated by **rest app** will be transformed by **etl app** and data will be stored into MySql database.

Stored data can be checked throw Admirer database management tool on [http://localhost:9092](http://localhost:9092) with folowing credentials:

* Server : db
* Username : smartcat
* Password : smartcat


# My solution - Bozidar Zavisic

In order to install necessary requirements please in your terminal run `pip install -r requirements.txt`. 
This will take the python dependencies from "requirements.txt" and install them. 


## rest_api_test.py
In this file I handled test cases covering logic of the rest API (port 9090).
I am checking different params being sent to the API itself, as well some other logic, such as:
- wrong shift ID being returned when creating a shift -> `test_check_shift_id()`
- wrong shift ID being returned when creating a shift breaks -> `test_check_shift_break_shift_id()`


To run test cases in this file please run `pytest -vv rest_api_test.py`

## etl_api_test.py
In this file I handled test cases covering logic of the etl API (port 9091).
I am checking different params being sent to the API itself. 

To run test cases in this file please run `pytest -vv etl_api_test.py`


## etl_connection_test.py
*I understand this may be out of scope, or potentially a security breach as I am no expert in docker, I just wanted to showcase my thinking when it comes to making assertions using the DB itself and making sure the data is correct.*

In this file I tested actual data in the database. Please keep in mind that in order to this I had to expose port 3306 in **docker-compose.yml** so I can make a connection to the DB in the test files. 

```
db:
    image: mysql
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
      - '3306:3306'
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: work_shifts
      MYSQL_USER: smartcat
      MYSQL_PASSWORD: smartcat
```

After you have made the changes to docker-compose.yml make sure to run `docker-compose up` for changes to take effect.

I didn't go into to much details when it comes to test cases as I did not know if in scope, but here is my logic:

The main function is `test_run_etl_job_valid_params()`. This function makes a request to the etl api with a given set of shift ids and an array of user ids. 
In my tests I expect:
1. All the combinations of shift_ids and user_ids have been created in the shifts table -> `test_run_etl_job_verify_shifts_data()`
2. Verify that every single shift gets a break (as indicated in the GenerateShift in WorkShiftService.java) -> `test_run_etl_job_verify_shift_breaks_data()`

This is just to show my reasoning, potential other test cases:
1. Verify all break data is correct (break start date and end need to be inbetween shift duration, etc..)
2. Verify allowances data (based on business logic)
3. Verify award interpretations data (based on business logic)

I also implemented the `clean_up_database()` function to have a clear start of DB for the sake of cleaner data and tests.

To run test cases in this file please run `pytest -vv etl_test_connection.py`
