import mysql.connector
from mysql.connector import Error
import requests
import logging


BASE_URL = "http://localhost:9091"


def execute_query(connection, query, operation='select'):
    print(query)

    cursor = connection.cursor(dictionary=True)
    try:
        cursor.execute(query)

        if operation.lower() == 'select':
            result = cursor.fetchall()
            return result
        elif operation.lower() == 'delete' or operation.lower() == 'update' or operation.lower() == 'insert':
            connection.commit()  # Commit changes for write operations
            return True
        else:
            logging.warning(f"Unsupported operation: {operation}")
            return None
    except Error as e:
        logging.error(f"Error executing query: {e}")
    finally:
        cursor.close()


def create_connection():
    connection = None

    db_config = {
        'host': 'localhost',
        'user': 'smartcat',
        'password': 'smartcat',
        'database': 'work_shifts',
    }

    try:
        connection = mysql.connector.connect(**db_config)
        if connection.is_connected():
            return connection
    except Error as e:
        print(f"Error: {e}")
    return connection


def close_connection(connection):
    if connection:
        connection.close()


def execute_db_query(query="", operation="select"):
    connection = create_connection()

    result = execute_query(connection, query, operation)

    close_connection(connection)

    return result


# For the sake of simplicity, every time we delete the data in the DB
def clean_up_database():
    execute_db_query('DELETE FROM shifts', 'delete')
    execute_db_query('DELETE FROM shift_breaks', 'delete')
    execute_db_query('DELETE FROM shift_award_interpretation', 'delete')
    execute_db_query('DELETE FROM shift_allowances', 'delete')


def contains_all_combinations(data, target_ids, target_user_ids):
    # Generate all combinations of target_ids and target_user_ids
    target_combinations = [(id_val, user_id_val)
                           for id_val in target_ids for user_id_val in target_user_ids]

    # Check if all combinations are present in the data
    for id_val, user_id_val in target_combinations:
        if not any(item['id'] == id_val and item['user_id'] == user_id_val for item in data):
            return False

    return True


def test_run_etl_job_verify_shifts_data():
    clean_up_database()
    shift_ids = [1, 2]
    user_ids = [3, 4]
    params = {"ids": ','.join(map(str, shift_ids)),
              "user_ids": ','.join(map(str, user_ids))}

    requests.get(f"{BASE_URL}/run", params=params)

    # If everything was working here we would get only shifts with given ids, but as they are generated randomly we must sellect all
    # shifts = execute_db_query(
    #     f"SELECT * FROM shifts WHERE `id` IN ({','.join(map(str, shift_ids))})", 'select')

    shifts = execute_db_query(
        f"SELECT * FROM shifts", 'select')

    # Because GenerateShifts in WorkShiftService.java generates 1 shift per user_id, here we want check all combinations
    all_combinations_created = contains_all_combinations(
        shifts, shift_ids, user_ids)

    assert all_combinations_created == True, f"Not all combinations of shift id and user_id have been created"


def test_run_etl_job_verify_shift_breaks_data():
    clean_up_database()
    shift_ids = [1, 2]
    user_ids = [3, 4]
    params = {"ids": ','.join(map(str, shift_ids)),
              "user_ids": ','.join(map(str, user_ids))}

    requests.get(f"{BASE_URL}/run", params=params)

    # If everything was working here we would get only shifts with given ids, but as they are generated randomly we must sellect all
    # shifts = execute_db_query(
    #     f"SELECT * FROM shifts WHERE `id` IN ({','.join(map(str, shift_ids))})", 'select')

    shifts = execute_db_query(
        f"SELECT * FROM shifts", 'select')

    for shift in shifts:
        # Because GenerateShift in WorkShiftService.java generates 1 break per Shift, here we check if the shift_break has been generated and if data inside it is valid
        shift_breaks = execute_db_query(
            f"SELECT * FROM `shift_breaks` WHERE `shift_id` = {shift['id']} AND `timesheet_id` = {shift['timesheet_id']} ", 'select')

        assert len(
            shift_breaks) == 1, f"Fewer or more than 1 shift break generated."
