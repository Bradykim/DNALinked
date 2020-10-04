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

    public LinkStrand(String s)
    {
        initialize(s);
    }
    public LinkStrand()
    {
        this("");
    }
    @Override
    public long size() {

        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst= new Node(source);
        myLast=myFirst;
        mySize =source.length();
        myAppends=0;
        myLocalIndex=0;
        myCurrent=myFirst;

    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next= new Node(dna);
        myLast= myLast.next;
        myAppends+=1;
        mySize+=dna.length();
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        Node rev = null;
        Node list = myFirst;
        int num=0;
        while (list != null) {
            num++;
            StringBuilder str = new StringBuilder();
            Node temp = list.next;
            list.next = rev;
            rev = list;
            list = temp;
            str.append(rev.info);
            str = str.reverse();
            String s = str.toString();
            rev.info=s;
        }
        if(num>1)
        {
            LinkStrand link = new LinkStrand(rev.info);
            rev= rev.next;
            while(rev != null)
            {
                link.append(rev.info);
                rev= rev.next;
            }
            return link;
        }


        return this;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {

        if(index == -1){
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
