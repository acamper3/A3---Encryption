
/*
 * Class: CMSC203
 * Instructor: Ping
 * Description: CryptoManager allows for 2 kinds of encryptions and decryptions:
 * cesars cypher and bellaso crypher
 * Due: 10/13/2022
 * Platform/compiler:
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
   Print your Name here: __Alex Campero________
*/

/** This is a utility class that encrypts and decrypts a phrase using two different approaches. The
 * first approach is called the Caesar Cipher and is a simple �substitution cipher� where characters
 * in a message are replaced by a substitute character. The second approach, due to Giovan Battista
 * Bellaso, uses a key word, where each character in the word specifies the offset for the
 * corresponding character in the message, with the key word wrapping around as needed.
 *
 * @author Farnaz Eivazi
 * @version 7/16/2022 */
public class CryptoManager {

    private static final char LOWER_RANGE= ' ';
    private static final char UPPER_RANGE= '_';
    private static final int RANGE= UPPER_RANGE - LOWER_RANGE + 1;

    /** This method determines if a string is within the allowable bounds of ASCII codes according
     * to the LOWER_RANGE and UPPER_RANGE characters
     *
     * @param plainText a string to be encrypted, if it is within the allowable bounds
     * @return true if all characters are within the allowable bounds, false if any character is
     *         outside */
    public static boolean isStringInBounds(String plainText) {
        boolean bool= false;
        for (int i= 0; i < plainText.length(); i++ ) {
            if (plainText.charAt(i) < LOWER_RANGE || plainText.charAt(i) > UPPER_RANGE) {
                return false;
            } else {
                bool= true;
            }
        }
        return bool;

    }

    /** Encrypts a string according to the Caesar Cipher. The integer key specifies an offset and
     * each character in plainText is replaced by the character \"offset\" away from it
     *
     * @param plainText an uppercase string to be encrypted.
     * @param key       an integer that specifies the offset of each character
     * @return the encrypted string */
    public static String caesarEncryption(String plainText, int key) {

        String offset= ""; // strings cant be changed. so a copy must be made

        if (isStringInBounds(plainText)) {
            for (int i= 0; i < plainText.length(); i++ ) {

                var ch= plainText.charAt(i) + key;
                while (ch > UPPER_RANGE) {
                    ch= ch - RANGE;
                    if (plainText.charAt(i) + key <= UPPER_RANGE) {
                        char bruh= (char) ch;
                        offset= offset + bruh;
                    }

                }

                if (ch < LOWER_RANGE) {
                    ch= key + RANGE;
                    offset= offset + Character.toString(ch);

                } else {
                    offset= offset + Character.toString(ch);
                }
            }
        } else {
            offset= "The selected string is not in bounds, Try again.";
        }
        return offset;

        /* for (int i= 0; i < plainText.length(); i++ ) {
        
            while (plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE) {
                var ch= Character.toString(plainText.charAt(i) + key);
                offset= offset.concat(ch);
            }
        
            while (plainText.charAt(i) < LOWER_RANGE) {
                var ch= Character.toString(key + RANGE);
                if (plainText.charAt(i) >= LOWER_RANGE) {
                    offset= offset.concat(ch);
                }
            }
        
            while (ch > UPPER_RANGE) {
                var bruh= Character.toString(key - RANGE);
                if (plainText.charAt(i) <= UPPER_RANGE) {
                    offset= offset.concat(bruh);
                }
        
            }
        
        }
        System.out.println(offset);
        return offset; */
    }

    /** Encrypts a string according the Bellaso Cipher. Each character in plainText is offset
     * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
     * to correspond to the length of plainText
     *
     * @param plainText  an uppercase string to be encrypted.
     * @param bellasoStr an uppercase string that specifies the offsets, character by character.
     * @return the encrypted string */
    public static String bellasoEncryption(String plainText, String bellasoStr) {
        // 1. extend length of bellasoStr(or shrink) to size of plainText
        String offset= "";
        while (bellasoStr.length() < plainText.length()) {
            bellasoStr= bellasoStr + bellasoStr;
        }

        if (bellasoStr.length() > plainText.length()) {
            bellasoStr= bellasoStr.substring(0, plainText.length());
        }

        for (int i= 0; i < plainText.length(); i++ ) {
            var ch= Character.toString(plainText.charAt(i) + bellasoStr.charAt(i));

            char bruh= (char) (plainText.charAt(i) + bellasoStr.charAt(i));

            /* while (ch > UPPER_RANGE) {
                ch= ch - RANGE;
                if (ch <= UPPER_RANGE) {
                    offset= offset + bruh;
                }
            } */
            if (isStringInBounds(ch)) {
                offset= offset + bruh;
            } else {
                while (bruh > UPPER_RANGE) {
                    bruh= (char) (bruh - RANGE);

                    if (bruh <= UPPER_RANGE) {

                        offset= offset + bruh;
                    }

                }

            }
        }
        return offset;

    }
    /*
    String offset= "";

    while (bellasoStr.length() < plainText.length()) {
        bellasoStr= bellasoStr + bellasoStr;

    }

    while (bellasoStr.length() > plainText.length()) {
        bellasoStr= bellasoStr.substring(0, bellasoStr.length() - 1);
    }

    // 2. char1of plaintext is replaced by offset of bellsoStr
    for (int i= 0; i < plainText.length(); i++ ) {

        while (bellasoStr.charAt(i) > UPPER_RANGE) {
            var ch= Character.toString(bellasoStr.charAt(i) + plainText.charAt(i) - RANGE);
            if (bellasoStr.charAt(i) <= UPPER_RANGE) {
                offset= offset.concat(ch);
            }
        }

        // if (bellasoStr.charAt(i) < LOWER_RANGE) {}
        var off= Character.toString(bellasoStr.charAt(i) + plainText.charAt(i));
        offset= offset.concat(off);
    }
    return offset;
    */

    /** Decrypts a string according to the Caesar Cipher. The integer key specifies an offset and
     * each character in encryptedText is replaced by the character \"offset\" characters before it.
     * This is the inverse of the encryptCaesar method.
     *
     * @param encryptedText an encrypted string to be decrypted.
     * @param key           an integer that specifies the offset of each character
     * @return the plain text string */
    public static String caesarDecryption(String encryptedText, int key) {
        String og= "";
        // take encrypted text and subtract the int to its character
        for (int i= 0; i < encryptedText.length(); i++ ) {
            var set= encryptedText.charAt(i) - key;

            while (set < LOWER_RANGE) {

                // add range to set
                set= set + RANGE;
                // set is an int, convert set to a char and concatenate to string
            }
            while (set > UPPER_RANGE) {
                // subtract range from set
                set= set - RANGE;

            }
            // concatenate the string to set
            og= og + Character.toString(set);
            // og= og + set; set is an int
        }
        return og;
    }

    /** Decrypts a string according the Bellaso Cipher. Each character in encryptedText is replaced
     * by the character corresponding to the character in bellasoStr, which is repeated to
     * correspond to the length of plainText. This is the inverse of the encryptBellaso method.
     *
     * @param encryptedText an uppercase string to be encrypted.
     * @param bellasoStr    an uppercase string that specifies the offsets, character by character.
     * @return the decrypted string */
    public static String bellasoDecryption(String encryptedText, String bellasoStr) {
        String og= "";
        // once again, must match to the # of letters in encrypted text

        while (bellasoStr.length() < encryptedText.length()) {
            bellasoStr= bellasoStr + bellasoStr;
        }

        if (bellasoStr.length() > encryptedText.length()) {
            bellasoStr= bellasoStr.substring(0, encryptedText.length());
        }

        for (int i= 0; i < encryptedText.length(); i++ ) {
            var set= encryptedText.charAt(i) - bellasoStr.charAt(i);

            while (set < LOWER_RANGE) {
                set= set + RANGE;
            }
            og= og + Character.toString(set);

        }
        return og;
    }
}
