# Assignment 4 — Graph Algorithms: SCC, DAG Shortest Path, Critical Path

## 📌 Course: Design & Analysis of Algorithms

This project implements:

- ✅ **Tarjan’s Algorithm** for Strongly Connected Components (SCC)
- ✅ **Condensation** of a directed graph into a DAG
- ✅ **Topological Sorting** (Kahn’s Algorithm)
- ✅ **Single-source shortest path** in DAG
- ✅ **Longest Path** (Critical Path) in DAG
- ✅ **Performance metrics** (DFS count, relaxations, runtime)

---

## 📂 Project Structure


assignment4/
├── src/main/java/
│ ├── common/ # Graph model + metrics + Main
│ ├── graph/scc/ # Tarjan SCC
│ ├── graph/topo/ # Topological Sort
│ └── graph/dagsp/ # DAG Shortest & Longest Path
├── data/ # Input graph test cases
└── scripts/ # Dataset generator


---

## ⚙️ How to Run

### Run on any dataset
To run the program on any dataset, use the following Maven command:

```bash
mvn exec:java -Dexec.mainClass="common.Main" -Dexec.args="data/<file>.json"

Example:
mvn exec:java -Dexec.mainClass="common.Main" -Dexec.args="data/tasks.json"
Running Tests
To run the JUnit tests, use:
mvn test

JUnit Tests
This project includes unit tests for the graph algorithms. The following tests have been implemented:
1️⃣ Graph Basics: Tests basic graph operations (e.g., adding edges, directed graph).
2️⃣ Tarjan’s SCC Algorithm: Tests the detection of strongly connected components.
3️⃣ Topological Sorting: Tests the topological ordering of a directed acyclic graph (DAG).
4️⃣ Shortest Path in DAG: Tests the shortest path calculation in a DAG.
5️⃣ Longest Path (Critical Path): Tests the calculation of the longest path (critical path) in a DAG.

All tests have been executed successfully with no errors or failures. See the full test results below.
| Test Class            | Tests Run | Failures | Errors | Time Taken |
| --------------------- | --------- | -------- | ------ | ---------- |
| `GraphBasicsTest`     | 1         | 0        | 0      | 0.003s     |
| `TarjanSCCTest`       | 1         | 0        | 0      | 0.002s     |
| `TopologicalSortTest` | 1         | 0        | 0      | 0.021s     |
| `DagPathsTest`        | 2         | 0        | 0      | 0.005s     |
| **Total**             | 5         | 0        | 0      | 0.031s     |


Summary Table
| Dataset    | Nodes | Edges | SCC Count | Topo    | Shortest | Longest (Critical Path) | Status |
| ---------- | ----- | ----- | --------- | ------- | -------- | ----------------------- | ------ |
| tasks.json | 8     | 7     | 6         | ✅       | ✅        | ✅                       | ✅      |
| small1     | 8     | 20    | 2         | ✅       | ✅        | ✅                       | ✅      |
| small2     | 8     | 10    | 8         | ✅       | ✅        | ✅                       | ✅      |
| small3     | 10    | 31    | 1         | trivial | trivial  | trivial                 | ✅      |
| medium1    | 15    | 54    | 2         | ✅       | ✅        | ✅                       | ✅      |
| medium2    | 18    | 43    | 18        | ✅       | ✅        | ✅                       | ✅      |
| medium3    | 20    | 136   | 1         | trivial | trivial  | trivial                 | ✅      |
| large1     | 30    | 114   | 1         | trivial | trivial  | trivial                 | ✅      |
| large2     | 40    | 104   | 40        | ✅       | ✅        | ✅                       | ✅      |
| large3     | 50    | 186   | 1         | trivial | trivial  | trivial                 | ✅      |

Note: Fully strongly-connected graphs condense into one node → trivial topo & paths.

Algorithms Used
1️⃣ Tarjan’s SCC Algorithm
Time Complexity: O(V + E)
Finds strongly connected components using DFS stack and low-link values.
2️⃣ Condensation Graph
Every SCC becomes a node → always a DAG.
3️⃣ Topological Sort
Algorithm: Kahn’s Algorithm
Time: O(V + E)
4️⃣ Shortest Path in DAG
Relax edges in topological order.
Time: O(V + E)
5️⃣ Longest Path (Critical Path)
Reverse relaxation: maximize instead of minimize.
Time: O(V + E)
Used to find critical task path in scheduling / CPM.

Metrics Collected
| Metric      | Meaning                        |
| ----------- | ------------------------------ |
| DFS         | DFS calls                      |
| edges       | Edges scanned                  |
| pushes/pops | Queue operations in Kahn       |
| relax       | Relax operations               |
| time (ms)   | Execution time in milliseconds |

Time Complexity Summary
| Operation         | Complexity   |
| ----------------- | ------------ |
| Tarjan SCC        | O(V + E)     |
| Build DAG         | O(V + E)     |
| Toposort          | O(V + E)     |
| DAG Shortest Path | O(V + E)     |
| DAG Longest Path  | O(V + E)     |
| **Total**         | **O(V + E)** |

Conclusion
SCC detection: Successfully implemented Tarjan's algorithm.
Condensation: Valid DAG constructed from SCCs.
Topological Sort: Correctly sorted the DAG.
Shortest & Longest Path: Validated on all datasets.
Performance: Linear-time complexity across all tasks.
Critical Path: Successfully computed.
👩‍💻 Author
Aisulu Azimkhan
