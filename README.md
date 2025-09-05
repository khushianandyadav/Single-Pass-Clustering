# Single-Pass-Clustering

This project implements a **Single-Pass Clustering Algorithm** in Java. It clusters documents based on **cosine similarity** of their term-frequency (TF) vectors.

## 📌 Features

* Computes **Term Frequency (TF)** for each document.
* Uses **Cosine Similarity** to measure document similarity.
* Groups documents into clusters using a **single-pass approach**.
* Adjustable **similarity threshold** (`0.5` by default).
* Example included with sample documents.

## 📂 Project Structure

```
SimpleSinglePassClustering.java
```

* **Cluster**: Represents a group of similar documents.
* **Document**: Stores words, TF vector, and document ID.
* **cosineSimilarity()**: Calculates similarity between two documents.
* **main()**: Demonstrates clustering with sample data.

## 🚀 How It Works

1. Each document is represented as a **TF vector**.
2. The algorithm compares each document with existing clusters:

   * If similarity ≥ threshold → add to that cluster.
   * Otherwise → create a new cluster.
3. Finally, it prints the clusters with their documents.

## 📝 Example Output

```
Cluster 1
  Document 1 : [apple, banana, orange]
  Document 3 : [apple, orange, fruit]
  Document 5 : [banana, apple, fruit]

Cluster 2
  Document 2 : [car, bus, train]
  Document 4 : [bus, train, transport]
```

## ⚙️ How to Run

1. Compile:

   ```bash
   javac SimpleSinglePassClustering.java
   ```
2. Run:

   ```bash
   java SimpleSinglePassClustering
   ```

## 📖 Applications

* Text clustering
* Document classification
* News/article grouping
* Topic detection

---
