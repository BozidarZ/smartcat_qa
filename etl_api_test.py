import requests
import pytest

BASE_URL = "http://localhost:9091"

def test_run_etl_job_valid_params():
    params = {"ids": "1,2,3", "user_ids": "1,2"}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 200, f"Run ETL job failed. Status code: {response.status_code}. Response content: {response.content}"
    assert "Successful" in response.text, f"Expected 'Successful' in response, but not found."

def test_run_etl_job_missing_params():
    # Test the case where required parameters are missing
    response = requests.get(f"{BASE_URL}/run")
    assert response.status_code == 500, f"Expected status code 500 for missing parameters, but got {response.status_code}. Response content: {response.content}"

def test_run_etl_job_invalid_params():
    # Test the case where parameters have invalid values
    params = {"ids": "invalid", "user_ids": "invalid"}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 500, f"Expected status code 500 for invalid parameters, but got {response.status_code}. Response content: {response.content}"

def test_run_etl_job_invalid_ids():
    # Test the case where invalid values are provided for 'ids'
    params = {"ids": "a,b,c", "user_ids": "1,2"}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 500, f"Expected status code 500 for invalid 'ids', but got {response.status_code}. Response content: {response.content}"

################################


def test_run_etl_job_successful_with_additional_params():
    # Test the case where additional parameters are provided
    params = {"ids": "1,2,3", "user_ids": "1,2", "additional_param": "value"}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 200, f"Run ETL job failed. Status code: {response.status_code}. Response content: {response.content}"
    assert "Successful" in response.text, f"Expected 'Successful' in response, but not found."

def test_run_etl_job_successful_with_empty_params():
    # Test the case where empty parameters are provided
    params = {"ids": "", "user_ids": ""}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 500, f"Expected status code 500 for empty parameters, but got {response.status_code}. Response content: {response.content}"

def test_run_etl_job_successful_with_large_params():
    # Test the case where large values are provided for parameters
    params = {"ids": "1" * 100000, "user_ids": "2" * 100000}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 400, f"Expected status code 400 for large parameters, but got {response.status_code}. Response content: {response.content}"

def test_run_etl_job_successful_with_valid_json():
    # Test the case where valid JSON is provided
    params = {"ids": "1,2,3", "user_ids": "1,2", "json_data": '{"key": "value"}'}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 200, f"Run ETL job failed. Status code: {response.status_code}. Response content: {response.content}"
    assert "Successful" in response.text, f"Expected 'Successful' in response, but not found."

def test_run_etl_job_successful_with_invalid_json():
    # Test the case where invalid JSON is provided
    params = {"ids": "1,2,3", "user_ids": "1,2", "json_data": 'invalid_json'}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 200, f"Expected status code 200 for invalid JSON, but got {response.status_code}. Response content: {response.content}"
    assert "Successful" in response.text, f"Expected 'Successful' in response, but not found."

def test_run_etl_job_successful_with_unicode_params():
    # Test the case where unicode characters are provided in parameters
    params = {"ids": "1,2,3", "user_ids": "1,2", "unicode_param": "漢字"}
    response = requests.get(f"{BASE_URL}/run", params=params)
    assert response.status_code == 200, f"Run ETL job failed. Status code: {response.status_code}. Response content: {response.content}"
    assert "Successful" in response.text, f"Expected 'Successful' in response, but not found."