package librarycontrolhw;

public class BookLinkedList<T> {
    Node head = null;
    public void add(T data){
     Node newNode = new Node(data);
        if(head==null){
       
    this.head= newNode;
    newNode=head;
    }
    else{
        Node temp = head;
    while(temp.next!=null){
        temp = temp.next;
    }
    temp.next=newNode;
    newNode.prev=temp;
    
    }
    }
    
    public void remove(T data){
   if(head==null){
       System.out.println("there is no any of book");
   }else{
       Node temp = head;
   while(temp.data!=data){
   temp = temp.next;
   }
   temp.prev.next=temp.next;
   temp.next.prev=temp.prev;
   }
    }
    
    public void seeList(){
    if(head==null){
        System.out.println("sorry but there is no books yaani");
    }
    else{
        Node temp = head;
    while(temp.next!=null){
        System.out.println(temp.data);
        temp=temp.next;
    }
        System.out.println(temp.data);
    }
    }
    
}
