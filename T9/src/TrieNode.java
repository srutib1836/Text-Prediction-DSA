import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single node in the T9 Trie.
 */
public class TrieNode {
    // Array to hold children pointers (indexed 0-9 for the keys)
    public TrieNode[] children;
    
    // List to hold all words that share the same T9 numeric code (textonyms)
    public List<String> words; 

    public TrieNode() {
        // Only 10 possible children (0-9)
        children = new TrieNode[10]; 
        words = new ArrayList<>();
    }
}