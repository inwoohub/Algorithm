LinkedList<String> list = new LinkedList<>();
list.add("A");
list.add("B");
list.add(1, "C");   // A, C, B
System.out.println(list.get(1)); // "C"



Queue<Integer> q = new LinkedList<>();
q.offer(10);
q.offer(20);
System.out.println(q.poll()); // 10
System.out.println(q.peek()); // 20


Deque<Integer> dq = new LinkedList<>();
dq.addFirst(1);  // [1]
dq.addLast(2);   // [1, 2]
System.out.println(dq.pollFirst()); // 1
System.out.println(dq.pollLast());  // 2


LinkedList<Integer> stack = new LinkedList<>();
stack.push(100);
stack.push(200);
System.out.println(stack.pop());  // 200
System.out.println(stack.peek()); // 100