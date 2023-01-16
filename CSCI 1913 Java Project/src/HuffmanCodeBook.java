/**
 * @Author Siyu Yang
 * The HuffmanCodeBook class is used to create a HuffmanCodeBook object which
 * is used to store the character variable and it's related BinarySequence.
 * char[] letters array is used to store the character; sequences array is used to
 * store the BinarySequence variable. total_num is used to count the
 * number of object in the above two array. contains_index is used to indicate
 * the index of a specific character in the array letters.
 */
public class HuffmanCodeBook{
    /**
     * char[] letters array is used to store the character; sequences array is used to
     * store the BinarySequence variable. total_num is used to count the
     * number of object in the above two array. contains_index is used to indicate
     * the index of a specific character in the array letters.
     */
    private char[] letters;
    private BinarySequence[] sequences;
    private int total_num;
    private int current_size;
    private int contains_index;
    private int[] index_list;

    /**
     * HuffmanCodeBook() is the constructor of this class, the main purpose
     * of this function create a new empty codebook object.
     */
    public HuffmanCodeBook(){
        total_num=0;
        letters=new char[0];
        sequences=new BinarySequence[0];
        current_size=0;
    }

    /**
     * addSequence function is used to add new character and it's BinarySequence
     * in the code book. I used binary search method here to sort the array.
     */
    public void addSequence(char c,BinarySequence seq){
        if (current_size==0){
            total_num=total_num+1;
            current_size=current_size+1;
            char[] update_letters=new char[current_size];
            BinarySequence[] update_sequences= new BinarySequence[current_size];
            update_letters[current_size-1]=c;
            update_sequences[current_size-1]=seq;
            letters=update_letters;
            sequences=update_sequences;
        }
        else if (total_num>=current_size){
            current_size=current_size*2;
            total_num=total_num+1;
            char[] update_letters=new char[current_size];
            BinarySequence[] update_sequences= new BinarySequence[current_size];
            for (int i=0;i<total_num-1;i++){
                update_letters[i]=letters[i];
                update_sequences[i]=sequences[i];
            }
            int lower=0;
            int higher=total_num-2;
            if (c>update_letters[total_num-2]){
                update_letters[total_num-1]=c;
                update_sequences[total_num-1]=seq;
                letters=update_letters;
                sequences=update_sequences;
            }
            else if (c<update_letters[0]){
                for(int j=total_num-1;j>0;j--){
                    update_letters[j]=update_letters[j-1];
                    update_sequences[j]=update_sequences[j-1];
                }
                update_letters[0]=c;
                update_sequences[0]=seq;
                letters=update_letters;
                sequences=update_sequences;
            }
            else {

                int mid=0;
                while (higher >= lower&update_letters[mid]!=c) {
                    mid = (higher + lower) / 2;
                    if (update_letters[mid] > c) {
                        higher = higher - 1;
                    } else if (update_letters[mid] < c) {
                        lower = lower + 1;
                    }
                }
                if (update_letters[mid]>c){
                    for(int k=total_num-1;k>mid;k--){
                        update_letters[k]=update_letters[k-1];
                        update_sequences[k]=update_sequences[k-1];
                    }
                    update_letters[mid]=c;
                    update_sequences[mid]=seq;
                }
                else if (update_letters[mid]<c&update_letters[mid]==c){
                    for(int f=total_num-1;f>mid+1;f--){
                        update_letters[f]=update_letters[f-1];
                        update_sequences[f]=update_sequences[f-1];
                    }
                    update_letters[mid+1]=c;
                    update_sequences[mid+1]=seq;
                }
                letters = update_letters;
                sequences = update_sequences;
            }
        }
        else if (total_num<current_size){
            total_num=total_num+1;
            int lower=0;
            int higher=total_num-2;
            if (c>letters[total_num-2]){
                letters[total_num-1]=c;
                sequences[total_num-1]=seq;
            }
            else if (c<letters[0]){
                for(int j=total_num-1;j>0;j--){
                    letters[j]=letters[j-1];
                    sequences[j]=sequences[j-1];
                }
                letters[0]=c;
                sequences[0]=seq;
            }
            else {

                int mid=0;
                while (higher >= lower&letters[mid]!=c) {
                    mid = (higher + lower) / 2;
                    if (letters[mid] > c) {
                        higher = higher - 1;
                    } else if (letters[mid] < c) {
                        lower = lower + 1;
                    }
                }
                if (letters[mid]>c){
                    for(int k=total_num-1;k>mid;k--){
                        letters[k]=letters[k-1];
                        sequences[k]=sequences[k-1];
                    }
                    letters[mid]=c;
                    sequences[mid]=seq;
                }
                else if (letters[mid]<c&letters[mid]==c){
                    for(int f=total_num-1;f>mid+1;f--){
                        letters[f]=letters[f-1];
                        sequences[f]=sequences[f-1];
                    }
                    letters[mid+1]=c;
                    sequences[mid+1]=seq;
                }
            }
        }
    }
    /**
     * The contains function is used to check whether a specific
     * character is in the array or not. In order to make the runtime
     * in O(log(n)), I used binary search method here.
     */
    public boolean contains(char letter){
        int lower=0;
        int higher=total_num-1;
        int mid=0;
        boolean check=false;
        if (letters.length==0){
            check=false;
        }
        else {
            if (letters[lower] == letter) {
                check = true;
                contains_index=lower;
            } else if (letters[higher] == letter) {
                check = true;
                contains_index=higher;
            } else if (letters[higher] < letter | letters[lower] > letter) {
                check = false;
            } else {
                while (higher >= lower & letters[mid] != letter) {
                    mid = (higher + lower) / 2;
                    if (letters[mid] > letter) {
                        higher = higher - 1;
                        check = false;
                    } else if (letters[mid] < letter) {
                        lower = lower + 1;
                        check = false;
                    } else if (letters[mid] == letter) {
                        check = true;
                        contains_index=mid;
                    }
                }
            }
        }
        return check;
    }

    /**
     * The containsAll function is used to check whether every letter in a word is in the
     * code book. The main method which I used here is contains function.
     */
    public boolean containsAll(String letters){
        boolean check=false;
        char[] letters_list=new char[letters.length()];
        index_list=new int[letters.length()];
        if (sequences.length>1&letters==""){
            check=true;
        }
        else {
            for (int i = 0; i < letters.length(); i++) {
                letters_list[i] = letters.charAt(i);
                check = contains(letters_list[i]);
                index_list[i] = contains_index;
            }
        }
        return check;
    }

    /**
     * getSequence function is used to find the BinarySequence
     * for a specific character.
     */
    public BinarySequence getSequence(char c){
        BinarySequence output=null;
        if (contains(c)==true){
            output=sequences[contains_index];
        }
        return output;
    }
    public BinarySequence encode(String s){
        BinarySequence output=new BinarySequence();
        containsAll(s);
        for (int i=0;i<s.length();i++){
            output.append(sequences[index_list[i]]);
        }
        return output;
    }

    /**
     *printChar function is a helper function which is used to return the specific
     * character in the codebook.
     */
    public char printChar(int index){
        return letters[index];
    }

    /**
     * printLength function is helper function which is used to print the total number of
     * item in the code book.
     * @return
     */
    public int printLength(){
        return total_num;
    }
}