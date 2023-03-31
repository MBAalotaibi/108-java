//
// Note: You are allowed to add additional methods if you need.
// Coded by Prudence Wong 2021-03-06
//
// Name: 
// Student ID: 
// MWS username: 
//
// Time Complexity and explanation: 
// f denotes initial cabinet size
// n denotes the total number of requests 
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
// 
// appendIfMiss():
// 	As we iterating each req list and comparing with each content in link
// list so . its time complexity is O (sizeof request list x sizeof of link list)
// moveToFront():
// 	As we iterating each req list and comparing with each content in link
// list so . its time complexity is O (sizeof request list x sizeof of link list)
// freqCount():
// 	As we iterating each req list and comparing with each content in link, also iterting each list for swap so
// list so  its time complexity is O (sizeof request list x sizeof of link list x size of link list )

class COMP108A2Cab {

	public COMP108A2Node head, tail;
	
	public COMP108A2Cab() {
		head = null;
		tail = null;
	}

	// append to end of list when miss
	public COMP108A2Output appendIfMiss(int rArray[], int rSize) {
		COMP108A2Output output = new COMP108A2Output(rSize, 1);
		
		COMP108A2Node curr;

		
		for(int i=0;i<rSize;i++) {
			curr = head;
			boolean found=false;
			while(curr!=null) {
				output.compare[i]++;
				if(rArray[i]==curr.data) {
					output.hitCount++;
					found=true;
					break;
				}
				curr= curr.next;	
			}
			if(found==false) {
				output.missCount++;
				insertTail(new COMP108A2Node(rArray[i]));
			}
		}
		
		
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}

	// move the file requested to the beginning of the list
	public COMP108A2Output moveToFront(int rArray[], int rSize) {
		COMP108A2Output output = new COMP108A2Output(rSize, 1);

		COMP108A2Node curr;

		
		for(int i=0;i<rSize;i++) {
			curr = head;
			boolean found=false;
			while(curr!=null) {
				output.compare[i]++;
				if(rArray[i]==curr.data) {
					output.hitCount++;
					found=true;
					if(tail==curr)
						tail=curr.prev;
					if (head == curr) {
				        head = curr.next;
				    }
				    if (curr.next != null) {
				       curr.next.prev = curr.prev;
				    }
				    if (curr.prev != null) {
				       curr.prev.next = curr.next;
				    }
					insertHead(new COMP108A2Node(rArray[i]));

					break;
				}
				curr= curr.next;	
			}
			if(found==false) {
				output.missCount++;
				insertHead(new COMP108A2Node(rArray[i]));
			}
		}
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;	
	}
	
	// move the file requested so that order is by non-increasing frequency
	public COMP108A2Output freqCount(int rArray[], int rSize) {
		COMP108A2Output output = new COMP108A2Output(rSize, 1);
		COMP108A2Node curr;

		
		for(int i=0;i<rSize;i++) {
			curr = head;
			boolean found=false;
			while(curr!=null) {
				output.compare[i]++;
				
				if(rArray[i]==curr.data) {
					output.hitCount++;
					found=true;
					COMP108A2Node temp=curr.prev;
					
					while(temp!=null) {
						if(temp.freq<=curr.freq) {
							if(temp==head) {
								head=curr;
								head.prev=null;
								temp.next=curr.next;
								curr.next.prev=temp;
								curr.next=temp;
								temp.prev=curr;
								break;
							}
							if(curr==tail) {
								tail=temp;
								tail.next=null;
								curr.prev=temp.prev;
								temp.prev.next=curr;
								curr.next=temp;
								temp.prev=curr;
								break;
							}
							else {
								temp.prev.next=curr;
								curr.prev=temp.prev;
								
								temp.next=curr.next;
								curr.next.prev=temp;
								curr.next=temp;
								temp.prev=curr;
								break;
							}
						}
						temp=temp.prev;	
					}
										
					
				    curr.freq++;
					break;
				}
				curr= curr.next;	
			}
			if(found==false) {
				output.missCount++;
				insertTail(new COMP108A2Node(rArray[i]));
			}
		}
		
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		output.cabFromHeadFreq = headToTailFreq();
		return output;		
	}

	
	
	
	
	
	// DO NOT change this method
	// insert newNode to head of list
	public void insertHead(COMP108A2Node newNode) {		

		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to tail of list
	public void insertTail(COMP108A2Node newNode) {

		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// delete the node at the head of the linked list
	public COMP108A2Node deleteHead() {
		COMP108A2Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
			else
				head.prev = null;
		}
		return curr;
	}
	
	// DO NOT change this method
	// empty the cabinet by repeatedly removing head from the list
	public void emptyCab() {
		while (head != null)
			deleteHead();
	}


	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTail() {
		COMP108A2Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	// Only to be used for output, do not use it to manipulate the list
	public String tailToHead() {
		COMP108A2Node curr;
		String outString="";
		
		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the frequency of the list nodes into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTailFreq() {
		COMP108A2Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.freq;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

}
