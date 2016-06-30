package com.shanshuan.book.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.shanshuan.book.app.MyApplication;

public class Cart implements Serializable{
	private List<CartItem> items;
	
	public Cart(){
		items=new ArrayList<CartItem>();
	}
	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public void addBook(Book book) {
		for (int i = 0; i < items.size(); i++) {
			CartItem ci=items.get(i);
			if(ci.getBook().equals(book)){
				ci.setBookCount(ci.getBookCount()+1);
				saveCart();
				return;
			}
			
		}
		CartItem item=new CartItem();
		item.setBook(book);
		item.setBookCount(1);
		items.add(item);
		saveCart();
	}
	
	public void removeItem(int position) {
		items.remove(position);
		saveCart();
	}
	
	
	public int getSumPrice() {
		int sum = 0;
		for (int i = 0; i < items.size(); i++) {
			CartItem item=items.get(i);
			int a=(int) ((item.getBook().getDangPrice())*(item.getBookCount()));
			sum+=a;
		}
		return sum;
	}
	public void addBookCount(int position) {
		CartItem item=items.get(position);
		item.addThisBookCount();
		saveCart();
	}
	public void removeBookCount(int position){
		CartItem item=items.get(position);
		item.removeThisBookCount();
		saveCart();
	}
	public void saveCart() {
		File file=new File(MyApplication.getContext().getCacheDir(), "Cart.info");
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(this);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Cart readCart(){
		File file=new File(MyApplication.getContext().getCacheDir(), "Cart.info");
		ObjectInputStream ois;
		Cart cart = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			cart=(Cart) ois.readObject();	
			Log.i("cart", cart.toString());
			ois.close();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(cart==null){
			Log.i("111", "cart==null");
			return new Cart();
		}else{
			return cart;
			
		}
	}
	@Override
	public String toString() {
		return "Cart [items=" + items + "]";
	}
	
	public String cartToString(){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			sb.append(item.getBook().getId()+","+item.getBookCount()+";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	public void removeAll() {
			items.clear();
			saveCart();
	}
}
