"""
Given an array of integers, return all triplets [a, b, c] such that a + b + c = 0.
The solution must not contain duplicate triplets e.g. [1, 2, 3] and [2, 3, 1] are
considered duplicate triplets. If no such triplets are found, return an empty array
"""
from typing import List

def brute_force(data: List[int]) -> List[List[int]]:
    """
    Brute force solution involes checking every possible triplet in the array to see
    if the sum is zero or not by using 3 nested for loops.
    """
    triplets = set()
    length = len(data)
    result = []
    for i in range(length):
        for j in range(i + 1, length):
            for k in range(j + 1, length):
                if data[i] + data[j] + data[k] == 0:
                    #print(f"index_{i}:{data[i]}, index_{j}:{data[j]}, index_{k}:{data[k]}")
                    triplet = tuple(sorted([data[i], data[j], data[k]]))
                    triplets.add(triplet)
    for item in triplets:
        result.append(list(item))
    return sorted(result)


def pair_sum_sorted(data:List[int], start:int, target:int)->List[List[int]]:
    pairs = []
    left = start
    right = len(data) - 1
    while left < right:
        pair_sum = data[left] + data[right]
        if pair_sum == target:
            pairs.append([data[left], data[right]])
            left += 1

            while left < right and data[left] == data[left - 1]:
                left += 1
        elif pair_sum < target:
            left += 1
        else:
            right -= 1
    return pairs


def triplet_sum(data: List[int]) -> List[List[int]]:
    triplets = []
    data.sort()
    for i in range(len(data)):
        # Optimization: Triplts consisting of only positive numbers will never sum to 0
        # Since the data is sorted already
        if data[i] > 0:
            break
        # To avoid duplicate triplet, skip the current number if it is the same as
        # the previous number
        if i > 0 and data[i] == data[i - 1]:
            continue
        # find all pairs where the sum of the pair equal to -'a'
        pairs = pair_sum_sorted(data, i + 1, -data[i])
        for pair in pairs:
            triplets.append([data[i]] + pair)

    return triplets


test_cases = [
    ([], []),
    ([0], []),
    ([1, -1], []),
    ([0, 0, 0], [[0, 0, 0]]),
    ([1, 0, 1], []),
    ([0, 0, 1, -1, 1, -1], [[-1, 0, 1]]),
    ([0, -1, 2, -3, 1], [[-3, 1, 2], [-1, 0, 1]])
]

for data, expected_result in test_cases:
    print(f"Running: {data} with expecetd result: {expected_result}")
    brute_force_actual = brute_force(data)
    assert len(brute_force_actual) == len(expected_result), f"\tBrute force error: Expected length: {len(expected_result)}. Actual length: {len(brute_force_actual)}. {expected_result}:{brute_force_actual}"
    assert brute_force_actual == expected_result, f"\tBrute force error:Expected: {expected_result}. Actual:{brute_force_actual}"
    print("\tPASSED BRUTE FORCE")
    triplet_sum_actual = triplet_sum(data)
    assert len(triplet_sum_actual) == len(expected_result), f"\tTriplet sum error: Expected length: {len(expected_result)}. Actual length: {len(triplet_sum_actual)}"
    assert triplet_sum_actual == expected_result, f"\tTriplet sum error:Expected: {expected_result}. Actual:{triplet_sum_actual}"
    print("\tPASSED TRIPLET SUM")
