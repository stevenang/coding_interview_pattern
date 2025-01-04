"""
A palindrome is a sequence of characters that reads the same forward and backward

Given a string, determine if it is palindrome after removing all non-alphanumeric
characters. A characters is alphanumeric if it is either letter or number
"""

test_data = [
    ["", True],
    ["a", True],
    ["aa", True],
    ["ab", False],
    ["!, (?)", True],
    ["12.02.2021", True],
    ["21.02.2021", False],
    ["Hello, world!", False]
]


def brute_force(input_string: str) -> bool:
    forward = []
    backward = []

    for letter in input_string:
        if letter.isalnum():
            forward.append(letter)
            backward.insert(0, letter)
        else:
            continue

    return forward == backward


def is_palindrome(input_string: str) -> bool:

    left = 0
    right = len(input_string) - 1

    while left < right:
        while left < right and not input_string[left].isalnum():
            left += 1
        while left < right and not input_string[right].isalnum():
            right -= 1
        if input_string[left] == input_string[right]:
            left += 1
            right -= 1
        else:
            return False

    return True

for input_string, expected_result in test_data:
    actual = brute_force(input_string)
    assert actual == expected_result, f"\tBrute force: Incorrect result. Expected: {expected_result}, Actual: {actual}"
    actual = is_palindrome(input_string)
    assert actual == expected_result, f"\tIs palindrome: Incorrect result. Expected: {expected_result}, Actual: {actual}"
    print(f"PASSED Palindrom Test: {input_string}")
