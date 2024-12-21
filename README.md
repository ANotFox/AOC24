# AOC24 Repository

## Overview
This repository contains my Java solutions for the [Advent of Code 2024](https://adventofcode.com/2024) challenges.  I am no longer actively working on this repository.

The solutions are organized by day and puzzle (`d1`, `d2`, ...) + (`p1`, `p2`, ...) -> (e.g., `d1p1.java` for Day 1, Puzzle 1).  The input data files are not included in the repository (see `.gitignore`).  You can find the corresponding input data on the Advent of Code website for the 2024 event.  Place any input files inside the `data` directory for the code to function. Note that some solutions might require specific formatting of the input data as described in each day's problem description on the Advent of Code site.

While these solutions work, they may not be the most optimal or elegant. They represent my progress and learning during the event (ie. a mix of algorithmic problem-solving, data parsing, and computational tasks).

---

## Repository Structure

The repository is organized as follows:

- **`d1p1.java` to `d9p2.java`**: Solutions for challenges, indexed by day (`d1` to `d9`) and part (`p1` or `p2`).
- **`template.java`**: A boilerplate template for creating new solutions.
- **`.gitignore`**: Specifies files and directories to be excluded from version control (`data/`, `.class` files, etc.).

Each solution typically:
1. Reads input from a corresponding file in the `data/` directory.
2. Implements problem-specific logic.
3. Prints the result and the execution time in microseconds.

---

## Prerequisites

- **Java Development Kit (JDK)**: Ensure that JDK 23 or above is installed to compile and run the programs. (Yes, I like to use newer JDKs)
- **Input Files**: The solutions rely on input files located in the `data/` directory. These files are not included in the repository and must be created for the programs to function. (Automating is not a high priority at present)
- **Input Format**: The format for the full proper input ought to be as follows `d1.txt` for Day 1. Similarly for test/example data, it ought to be `d1test.txt`. Similarly for other days.
  

---

## How to Use

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd AOC24
   ```

2. Compile and run a specific solution:
   ```bash
   javac d1p1.java
   java d1p1
   ```

3. Provide the required input file in the `data/` directory (e.g., `data/d1.txt` for `d1p1.java`).

4. You will have to currently manually change the input file in the specific day's programme (e.g substitute example data instead of the full input data)

---

## Future Improvements

- **Documentation**: Add problem descriptions for each solution to make the repository self-explanatory and comprehensive.
- **Optimisation**: Refactor and optimize existing code for better performance and readability.
- **Testing**: Implement unit tests for each solution to ensure correctness (using both the test input from the website and sample examples).

---

## License

This repository does not specify a license. For reuse or contributions, please contact me instead ^_____^.

---

## Status

**Inactive**: No active development or maintenance is currently ongoing for this repository.
