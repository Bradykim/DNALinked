public class LinkStrand implements IDnaStrand
{
    private class Node{
        String info;
        Node next;
        public Node(String s){
            info=s;
            next=null;
        }
    }

    private Node myFirst;
    private Node myLast;
    private long mySize;
    private int myAppends;
    private Node myCurrent;
    private int myIndex;
    private int myLocalIndex;

    /**
     * Create a LinkStrand representing s. No error checking is done to see if s
     * represents valid genomic/DNA data.
     *
     * @param s is the source of cgat data for this strand
     */
    public LinkStrand(String s)
    {
        initialize(s);
    }


    public LinkStrand()
    {
        this("");
    }

    /**
     * @return number of String characters in in this LinkStrand object.
     */
    @Override
    public long size() {

        return mySize;
    }

    /**
     * Initialize this strand so that it represents the value of source. No
     * error checking is performed.
     * Also initializes the value of each field in the LinkStrand class.
     *
     * @param source is the source of this enzyme
     */
    @Override
    public void initialize(String source) {
        myFirst= new Node(source);
        myLast=myFirst;
        mySize =source.length();
        myAppends=0;
        myLocalIndex=0;
        myCurrent=myFirst;
        myIndex=0;

    }

    /**
     * Gets an instance of this LinkStrand.
     *
     * @param source is the source of this enzyme
     */
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    /**
     * Simply append a strand of dna data to this LinkStrand. No error checking is
     * done. It adds a new Node with the given String to the list.
     *
     * @param dna is the String appended to this strand
     */
    @Override
    public IDnaStrand append(String dna) {
        myLast.next= new Node(dna);
        myLast= myLast.next;
        myAppends+=1;
        mySize+=dna.length();
        return this;
    }

    /**
     * Takes a LinkStrand and reverses each node within the link and reverses
     * each of the Strings in each node. This gets a total reversal of the entire dna
     * set.
     *
     * @return LinkStrand that is the reverse of the original.
     */
    @Override
    public IDnaStrand reverse() {
        Node rev = null;
        Node list = myFirst;
        while (list != null) {
            StringBuilder str = new StringBuilder();
            Node temp = rev;
            str.append(list.info);
            str.reverse();
            String s = str.toString();
            rev = new Node(s);
            rev.next = temp;
            list=list.next;
        }

        LinkStrand link = new LinkStrand(rev.info);
        rev= rev.next;
        while(rev != null)
        {
            link.append(rev.info);
            rev= rev.next;
        }
        return link;



    }

    /**
     * Returns the amount of times a new node has been appended to the end of the list.
     *
     * @return an int that is the number of times append has been called.
     */
    @Override
    public int getAppendCount() {
        return myAppends;
    }

    /**
     * Takes an index number and searches through each String character
     * in each node of the LinkStrand to find the character at that given
     * index and returns the character.
     *
     * @param index is the index of the character within the LinkStrand
     * @return the character at that given index within the LinkStrand
     */
    @Override
    public char charAt(int index) {

        if(index < 0){
            throw new IndexOutOfBoundsException();
        }
        int previousstartindex = myIndex-myLocalIndex;
        if(previousstartindex>index)
        {
            previousstartindex=0;
            myCurrent=myFirst;

        }
        myLocalIndex=index-previousstartindex;
        myIndex= index;
        while (myCurrent.info.length()-1<myLocalIndex) {
            myLocalIndex-=myCurrent.info.length();

            if(myCurrent.next==null){
                throw new IndexOutOfBoundsException();
            }
            myCurrent = myCurrent.next;

        }
        return myCurrent.info.charAt(myLocalIndex);



    }

    /**
     * Returns the String value of this LinkStrand using the StringBuilder function.
     *
     * @return an String that has the same values as the strand it is applied to.
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        Node current = myFirst;
        while(current!=null){
            str.append(current.info);
            current=current.next;
        }
        String s = str.toString();
        return s;
    }
}
