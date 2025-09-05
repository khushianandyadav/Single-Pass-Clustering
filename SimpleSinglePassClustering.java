import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.List; 
import java.util.Map; 
import java.util.Set; 
 
public class SimpleSinglePassClustering { 
 
    static final double SIMILARITY_THRESHOLD = 0.5; 
 
    static class Cluster { 
        List<Document> documents = new ArrayList<>(); 
    } 
 
    public static void main(String[] args) { 
        // Input: 5 documents as arrays of words 
        String[][] documents = { 
            {"apple", "banana", "orange"}, 
            {"car", "bus", "train"}, 
            {"apple", "orange", "fruit"}, 
            {"bus", "train", "transport"}, 
            {"banana", "apple", "fruit"} 
        }; 
 
        // Convert to List of Document objects 
        List<Document> docList = new ArrayList<>(); 
        for (int i = 0; i < documents.length; i++) { 
            docList.add(new Document(i + 1, documents[i])); 
        } 
 
        // Create TF map for each document 
        for (Document doc : docList) { 
            doc.computeTF(); 
        } 
 
        // Perform Single-pass Clustering 
        List<Cluster> clusters = new ArrayList<>(); 
 
        for (Document doc : docList) { 
            boolean added = false; 
 
            for (Cluster cluster : clusters) { 
                Document rep = cluster.documents.get(0); 
                double similarity = cosineSimilarity(doc.tf, rep.tf); 
                if (similarity >= SIMILARITY_THRESHOLD) { 
                    cluster.documents.add(doc); 
                    added = true; 
                    break; 
                } 
            } 
 
            if (!added) { 
                Cluster newCluster = new Cluster(); 
                newCluster.documents.add(doc); 
                clusters.add(newCluster); 
            } 
        } 
 
        // Print clusters 
        int clusterId = 1; 
        for (Cluster cluster : clusters) { 
            System.out.println("Cluster " + clusterId++); 
            for (Document doc : cluster.documents) { 
                System.out.println("  Document " + doc.id + " : " + Arrays.toString(doc.words)); 
            } 
        } 
    } 
 
    static double cosineSimilarity(Map<String, Double> vec1, Map<String, Double> vec2) { 
        Set<String> allTerms = new HashSet<>(); 
        allTerms.addAll(vec1.keySet()); 
        allTerms.addAll(vec2.keySet()); 
 
        double dotProduct = 0.0; 
        double norm1 = 0.0; 
        double norm2 = 0.0; 
 
        for (String term : allTerms) { 
            double v1 = vec1.getOrDefault(term, 0.0); 
            double v2 = vec2.getOrDefault(term, 0.0); 
            dotProduct += v1 * v2; 
            norm1 += v1 * v1; 
            norm2 += v2 * v2; 
        } 
 
        return (norm1 == 0 || norm2 == 0) ? 0.0 : dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2)); 
    } 
 
    static class Document { 
        int id; 
        String[] words; 
        Map<String, Double> tf = new HashMap<>(); 
 
        Document(int id, String[] words) { 
            this.id = id; 
            this.words = words; 
        } 
 
        void computeTF() { 
            Map<String, Integer> freq = new HashMap<>(); 
            for (String word : words) { 
                freq.put(word, freq.getOrDefault(word, 0) + 1); 
            } 
            for (Map.Entry<String, Integer> entry : freq.entrySet()) { 
                tf.put(entry.getKey(), entry.getValue() / (double) words.length); 
            } 
        } 
    } 
}