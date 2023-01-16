/**
 * @Author Siyu Yang
 * The HuffmanNode class is used to create and modify a
 * HuffmanNode object.
 */
public class HuffmanNode {
    /**
     * zero, one variable are the child node of the current node.
     * node variable is the data of the current node.
     * overall_check is a boolean variable which used to check whether
     * the current tree is vaild or not.
     */
    private HuffmanNode zero;
    private HuffmanNode one;
    private Character node;
    private boolean overall_check;

    /**
     *The HuffmanNode function is the constructor of this class.
     * We will set the value of zero and one node in this constructor.
     */
    public HuffmanNode(HuffmanNode zero,HuffmanNode one){
        this.zero=zero;
        this.one=one;
        node=null;
    }

    /**
     * The HuffmanNode function here is another constructor for this class.
     * It used create a not empty node with a two null child node.
     */
    public HuffmanNode(char data){
        node=data;
        zero=null;
        one=null;
    }

    /**
     *getZero function will return node zero.
     */
    public HuffmanNode getZero(){
        return zero;
    }

    /**
     *setZero will replace the current zero with the input new zero.
     */
    public void setZero(HuffmanNode zero){
        this.zero=zero;
    }

    /**
     *getOne function will return node One.
     */
    public HuffmanNode getOne(){
        return one;
    }

    /**
     *SetOne will replace the current one with the input new one.
     */
    public void setOne(HuffmanNode one){
        this.one=one;
    }

    /**
     * getData function will return the current node's data.
     */
    public Character getData(){
        return node;
    }

    /**
     * setData will set the new data for the current node.
     * @param data
     */
    public void setData(char data){
        this.node=data;
    }

    /**
     * isLeaf is used to check whether the current
     * node isLeaf.
     * @return
     */
    public boolean isLeaf(){
        boolean check=false;
        if ((zero==null|one==null)&node!=null){
            check=true;
        }
        return check;
    }

    /**
     * This is a helper function which used to do the recursion for isVaild function.
     */
    public void recursion(HuffmanNode new_one){
        if (new_one.isLeaf()==true){
            if (new_one.getZero()!=null|new_one.getOne()!=null){
                overall_check=false;
            }
        }
        else if(overall_check!=false){
            if (new_one.getData()==null&(new_one.getZero()==null|new_one.getOne()==null)){
                overall_check=false;
            }
            else {
                for (int i = 0; i < 2; i++) {
                    if (i == 0&new_one.getZero()!=null) {
                        recursion(new_one.getZero());
                    } else if (i == 1&new_one.getOne()!=null) {
                        recursion(new_one.getOne());
                    }
                }
            }
        }
    }

    /**
     * isVaild function is used to check whether the current Huffman Tree is correct or not.
     */
    public boolean isValid(){
        overall_check=true;
        if(isLeaf()==true){
            if(getZero()!=null|getOne()!=null){
                overall_check=false;
            }
        }
        else if (overall_check!=false){
            if ((getData()==null&getOne()==null)|(getData()==null&getZero()==null)){
                overall_check=false;
            }
            else{
                for (int i = 0; i < 2; i++) {
                    if (i == 0&zero!=null) {
                        recursion(zero);
                    } else if (i == 1&one!=null) {
                        recursion(one);
                    }
                }
            }
        }
        return overall_check;
    }
}
