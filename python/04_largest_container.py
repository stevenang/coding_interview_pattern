"""
You are given an array of numbers, each representing the height of a vertical line
on a graph. A container can be formed with any pair of these lines, along
with the x-axis of the graph. Return the amount of water which the largest container can hold
"""
from typing import List


def brute_force(heights: List[int]):
    length = len(heights)
    max_water = 0
    # Find the maximum amount of water stored between all pairs of lines
    # If we have two vertical lines, height[i] and heights[j], the amount 
    # of water can be hold between these two lines is min(height[i], height[j])
    # * (j - 1) where j - 1represent the width of the container
    for i in range(length):
        for j in range(i + 1, length):
            width = j - i
            water = min(heights[i], heights[j]) * width
            max_water = max(max_water, water)
    return max_water


def largest_container(heights: List[int]):
    max_water = 0
    left = 0
    right = len(heights) - 1
    while left < right:
        water = min(heights[left], heights[right]) * (right - left)
        max_water = max(max_water, water)
        if heights[left] < heights[right]:
            left += 1
        elif heights[left] > heights[right]:
            right -= 1
        else:
            left += 1
            right -= 1

    return max_water

test_cases = [
    ([], 0),
    ([1], 0),
    ([0, 1, 0], 0),
    ([3, 3, 3, 3], 9),
    ([1, 2, 3], 2),
    ([3, 2, 1], 2),
    ([2, 7, 8, 3, 7, 6], 24)
]

for heights, expected_result in test_cases:
    actual = brute_force(heights)
    assert actual == expected_result, f"\tBrute force: Incorrect result. Expected: {expected_result}. Actual: {actual}"
    print(f"Brute force: Calculating largest container for : {heights}. Result: {actual}")
    actual = largest_container(heights)
    assert actual == expected_result, f"\tLargest container: Incorrect result. Expected: {expected_result}. Actual: {actual}"
    print(f"Largest container: Calculating largest container for : {heights}. Result: {actual}")
