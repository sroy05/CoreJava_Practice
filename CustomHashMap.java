package com.stream.java;


public class CustomHashMap<K,V> implements AutoCloseable{
	private Entry<K,V> [] table=null;
	private static final int INITIAL_CAPACITY = 1 << 2; // 16
	private int size = 0;
	Object locks;
	public CustomHashMap() {
		this(INITIAL_CAPACITY);
	}

	public CustomHashMap(int initialCapacity) {
		// TODO Auto-generated constructor stub		
		this.table=new Entry[initialCapacity];
		this.locks=new Object();
	}
	
	
	public void put(K key, V value) throws HashMapOverFlowException {
		Entry<K,V> entry=new Entry<K,V>(key,value);
		boolean locationFound=false;
		int bucket=getHash(key)%INITIAL_CAPACITY;
		if(bucket<0) {
			bucket=bucket * (-1);
		}

		if(table[bucket]==null && locationFound==false) {

			synchronized (locks) {
				System.out.println("No Collision for [key]:"+key+"added key at location: "+bucket);
				table[bucket]=new Entry<K,V>(key, value);
				locks.notifyAll();
				locationFound=true;
			}
		}
		else if(table[bucket]!=null && locationFound==false) {
			
				synchronized (locks) {
					for(int i=bucket+1;i<INITIAL_CAPACITY;i++) {
						if(table[i]==null) {
							table[i]=new Entry<K,V>(key, value);
							locationFound=true;
							System.out.println("added key at location: "+i);
							locks.notifyAll();
						}
						if(locationFound)  {
							Throwable t=new Throwable();
							throw new HashMapOverFlowException("Hashmap memory overloaded",t.fillInStackTrace());
						}

					}
				}
			}
			
		}
	
	public V getValue(K key) {
		Entry<K,V> data=null;
		int index=getHash(key)%INITIAL_CAPACITY;;
		for(int i=0;i<INITIAL_CAPACITY;i++) {
			if(i==index) {
				 data=table[i];
				
			}
		}
		return data.getValue();
		
	}
	private int getHash(K key) {
		// TODO Auto-generated method stub
		 int h;
		System.out.println("Index location:"+((h = key.hashCode()) ^ (h >>> 16))%INITIAL_CAPACITY);
		
	        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	public static void main(String[] args) {
		
		CustomHashMap<String,String> cs= new CustomHashMap<String, String>();
		try {
			cs.put("AB","AB");
			cs.put("BD","B");
			cs.put("D", "D");
			cs.put("E", "E");
			cs.put("H", "H");
			cs.put("J", "J");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("Get Value:"+cs.getValue("AB"));
		}
		

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
