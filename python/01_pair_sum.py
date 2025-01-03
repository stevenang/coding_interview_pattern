"""
Given an array of integers sorted in ascending order and target value, return the indexes
of any pair of numbers in the aaray that sum to the target. The order of theindexes in the
result doesn't matter. If no pair found, return an empty array
"""

from typing import List

test_cases = [
    ([], 0, []),
    ([1], 1, []),
    ([2, 3], 5, [0, 1]),
    ([2, 4], 5, []),
    ([2, 2, 3], 5, [0, 2]),
    ([-1, 2, 3], 2, [0, 2]),
    ([-3, -2, -1], -5, [0, 1])
]


def brute_force(data:List[int], target:int) -> List[int]:
    """
    The propblem can be solved by iterating each element using a nested for loop
    to check all possible pair. The outer loop that traverse the array for the first element
    of the pair, and an inner loop that traverse the rest of the array to find the second
    element of the pair
    """
    if len(data) < 2:
        return []
    for index_1, n1 in enumerate(data):
        for index_2, n2 in enumerate(data[index_1 + 1:]):
            if n1 + n2 == target:
                return [index_1, index_2 + 1]
    return []


def two_pointers(data:List[int], target: int) -> List[int]:
    if len(data) < 2:
        return []

    left = 0
    right = len(data) - 1

    while left < right:
        actual = data[left] + data[right]
        if actual == target:
            return [left, right]
        elif actual < target:
            left += 1
        else:
            right -= 1

    return []


for data, target, expected_result in test_cases:
    assert brute_force(data, target) == expected_result, f"brute_force failed. Expected: {expected_result}. Actual: {brute_force(data, target)}"
    assert two_pointers(data, target) == expected_result, "two_pointers failed. Expected: {expected_result}. Actual: {brute_force(data, target)}"
