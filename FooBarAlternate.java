package com.stream.java;

public class FooBarAlternate {

	 private int n;
	 int count=1;
	 int MAX=10;
	    boolean flag=true;
	    
	    public FooBarAlternate(int n) {
	        this.n = n;
	    }

	    public void foo() throws InterruptedException {       
	     synchronized(this){
	    	 while(count<MAX) {
	    		 while(!flag) {
	    			 wait();
	    		 }
	                System.out.println("foo");
	                 flag=!flag;
	                 notify();
	                 count++;
	    	 }
	        }

	     }   
	        
	    

	    public void bar() throws InterruptedException {
	    	 synchronized(this){
		    	 while(count<MAX) {
		    		 while(flag) {
		    			 wait();
		    		 }
		                System.out.println("bar");
		                 flag=!flag;
		                 notify();
		                 count++;
		    	 }
		        }
	        
	    }
	    
	    public static void main(String [] args) {
	    	FooBarAlternate foobar =new FooBarAlternate(10);
	    	Thread t1=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						foobar.foo();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	    		
	    	});
	    	
	    	Thread t2=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						foobar.bar();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	    		
	    	});
	    	
	    	t1.start();
	    	t2.start();
	    	
	    	try {
	    		
	    		t1.join();
	    		t2.join();
	    	}catch(InterruptedException e) {
	    		System.out.println(e.getMessage());
	    	}
	    	
	    }
}
