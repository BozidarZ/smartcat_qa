import requests

BASE_URL = "http://localhost:9090"  # Replace with your actual base URL


def test_get_shifts_valid_params():
    params = {"ids": "1,2,3", "user_ids": "1,2"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Get shifts failed. Status code: {response.status_code}. Response content: {response.content}"


def test_get_shifts_invalid_params():
    params = {"ids": "invalid", "user_ids": "invalid"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 500, f"Expected status code 500 for invalid parameters, but got {response.status_code}. Response content: {response.content}"


def test_get_shifts_invalid_ids():
    params = {"ids": "a,b,c", "user_ids": "1,2"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 500, f"Expected status code 500 for invalid 'ids', but got {response.status_code}. Response content: {response.content}"


def test_get_shifts_successful_with_additional_params():
    params = {"ids": "1,2,3", "user_ids": "1,2", "additional_param": "value"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Get shifts failed. Status code: {response.status_code}. Response content: {response.content}"


def test_get_shifts_successful_with_valid_json():
    params = {"ids": "1,2,3", "user_ids": "1,2",
              "json_data": '{"key": "value"}'}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Get shifts failed. Status code: {response.status_code}. Response content: {response.content}"


def test_get_shifts_successful_with_invalid_json():
    params = {"ids": "1,2,3", "user_ids": "1,2", "json_data": 'invalid_json'}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Expected status code 200 for invalid JSON, but got {response.status_code}. Response content: {response.content}"


def test_get_shifts_successful_with_unicode_params():
    params = {"ids": "1,2,3", "user_ids": "1,2", "unicode_param": "漢字"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Get shifts failed. Status code: {response.status_code}. Response content: {response.content}"


def test_check_shift_id():
    # Noticed that returned shift IDs don't match the sent parameters when calling the API

    shift_id = "1"
    params = {"ids": shift_id, "user_ids": "1,2"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Get shifts failed. Status code: {response.status_code}. Response content: {response.content}"

    shifts = response.json()

    for shift in shifts:
        assert shift['id'] == shift_id, f"Created shift id: {shift['id']} does not match shift_id parameter: {shift_id}"


def test_check_shift_break_shift_id():
    # Noticed that returned shift IDs in the breaks array do not match the parent shift id.

    shift_id = "1"
    params = {"ids": shift_id, "user_ids": "1,2"}
    response = requests.get(f"{BASE_URL}/shifts", params=params)
    assert response.status_code == 200, f"Get shifts failed. Status code: {response.status_code}. Response content: {response.content}"

    shifts = response.json()

    for shift in shifts:
        for shift_break in shift['breaks']:
            assert shift_break['shift_id'] == shift[
                'id'], f"Created shift break id: {shift_break['id']}, has shift_id: { shift_break['shift_id']} which does not match shift id on the parent object: {shift['id']}"
