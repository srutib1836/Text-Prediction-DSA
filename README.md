# DSA-01-TextPrediction

# 📱 T9 Predictive Text Simulator (Java + Swing + Trie)

A simple **Java Swing GUI application** that simulates the classic **T9 predictive text system** used in early mobile phones.  
It uses a **Trie data structure** to store and predict possible words based on numeric keypad input.

---

## 🧠 Features
✅ Interactive GUI keypad (like old mobile phones)  
✅ Predicts possible words for numeric sequences  
✅ Supports cycling between multiple word predictions  
✅ Deletes and resets input easily  
✅ Loads dictionary from a text file (`dictionary.txt`)  
✅ Efficient Trie-based implementation for fast lookups  

---

## 📸 Example

| Numeric Input | Predicted Words |
|----------------|-----------------|
| 43556 | hello |
| 4663 | good, home, gone |
| 228 | cat, bat, act |

---

## ⚙️ How It Works

Each key on the keypad maps to letters:

| Key | Letters |
|-----|----------|
| 2 | a b c |
| 3 | d e f |
| 4 | g h i |
| 5 | j k l |
| 6 | m n o |
| 7 | p q r s |
| 8 | t u v |
| 9 | w x y z |

Words are inserted into a **Trie** where each node corresponds to a numeric key (0–9).  
When the user types a sequence (like `43556`), the app traverses the Trie and retrieves all words that match that path.

---

## 🏗️ Project Structure

