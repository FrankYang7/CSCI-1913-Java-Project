/**
 * @Author Siyu Yang
 * The HuffmanCodeTree class is used to create a Tree which contain the character element in
 * a codebook or by the user themselves.
 */
public class HuffmanCodeTree {
    /**
     * The root variable is the only private variable, it used to store the value if root Node.
     */
    private HuffmanNode root;

    /**
     * The first HuffmanTree function is the constructor which used to store the
     * user given root node.
     */
    public HuffmanCodeTree(HuffmanNode root){
        this.root=root;
    }

    /**
     * The second HuffmanCodeTree function is the constructor which used to create
     * a HuffmanCode Tree based on the given code book.
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook){
        root=new HuffmanNode(null,null);
        for (int i=0;i<codebook.printLength();i++){
            char current_letter=codebook.printChar(i);
            put(codebook.getSequence(current_letter),current_letter);
        }
    }

    /**
     * isVaild function is used check whether the current tree is vaild or not.
     */
    public boolean isValid(){
        boolean check=root.isValid();
        return check;
    }

    /**
     * put function is used to add the new character in the tree by follow its BinarySequence.
     */
    public void put(BinarySequence seq, char letter){
        String seq_string=seq.toString();
        String[] seq_list=seq_string.split("");
        HuffmanNode target_node=root;
        for (int i=0;i<seq_list.length;i++){
            if (seq_list[i].equals("0")){
                if (target_node.getZero()!=null){
                    target_node=target_node.getZero();
                }
                else{
                    HuffmanNode new_node=new HuffmanNode(null,null);
                    target_node.setZero(new_node);
                    if (i==0){
                        root=target_node;
                    }
                    target_node=target_node.getZero();
                }
            }
            else if (seq_list[i].equals("1")){
                if (target_node.getOne()!=null) {
                    target_node = target_node.getOne();
                }
                else{
                    HuffmanNode new_node=new HuffmanNode(null,null);
                    target_node.setOne(new_node);
                    if (i==0){
                        root=target_node;
                    }
                    target_node=target_node.getOne();
                }
            }
        }
        target_node.setData(letter);
    }

    /**
     * The decode function is used to generate a message by following the given BinarySequence's boolean.
     */
    public String decode(BinarySequence s){
        HuffmanNode node=root;
        String output="";
        String s_strings=s.toString();
        String[] s_list=s_strings.split("");
        for (int i=0;i<s_list.length;i++){
            if (s_list[i].equals("0")){
                node=node.getZero();
                if (node.isLeaf()){
                    output=output+node.getData();
                    node=root;
                }
            }
            else if(s_list[i].equals("1")){
                node=node.getOne();
                if (node.isLeaf()){
                    output=output+node.getData();
                    node=root;
                }
            }
        }
        return output;
    }

}
