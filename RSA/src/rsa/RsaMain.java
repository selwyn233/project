package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Media;

public class RsaMain {

	/**
	 * @param args
	 */
	public static boolean isPrime(int a) {//判断素数	  
        boolean flag = true;    
        if (a < 2) {// 素数不小于2  
            return false;  
        } else {    
            for (int i = 2; i <= Math.sqrt(a); i++) {    
                if (a % i == 0) {// 若能被整除，则说明不是素数，返回false    
                    flag = false;  
                    break;// 跳出循环  
                }  
            }  
        }  
        return flag;  
    }  
	public static int d(int p,int q,int e){//计算出d的值
		int phi,d=0;
		phi = (p-1)*(q-1);
		while(e*d%phi!=1){
			d++;
		}
		return d;
	}
	public static int jug(String[] str,int n) {//判断加密的数组是否大于n
		int j,i=0;
		int[] t = new int[500];

		for(j = 0; j < str.length; j++){
			t[j] = Integer.parseInt(str[j]);
			if(t[j]<n){
				i=1;
			}
			else {
				System.out.print("重新输入加密数组:");
				i=2;
				break;
			}
		}
		return i;
	}
	public static List C(String[] str,int n,int e){//得出密文
		int j;
		List li = new ArrayList();
		for(j=0;j<str.length;j++){
			String k = str[j];
			String y = Integer.toString(n);
			BigInteger a=new BigInteger(k);
			BigInteger c=new BigInteger(y);
			a = (a.pow(e)).remainder(c);
			li.add(a);
		}
		return li;
	}
	public static List M(String[] str,int d,int n){//得出明文
		int j;
		List li = new ArrayList();
		for(j=0;j<str.length;j++){
			String k = str[j];
			String y = Integer.toString(n);
			BigInteger a=new BigInteger(k);
			BigInteger c=new BigInteger(y);
			a = (a.pow(d)).remainder(c);
			li.add(a);
		}
		return li;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("本算法选定的e为3");
		System.out.println("输入两个素数：");
		int p=0,q=0,i,n,phi,j=0,m = 0,d=0,e=3;
		List li = new ArrayList();
		String tmp;
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		i=1;
		while(i==1){
			p = sc.nextInt();
			q = sc.nextInt();
			if(isPrime(p)&&isPrime(q)){
				i=0;
			}
			else {
				System.out.println("你输入的两个数不符合要求!");
				System.out.print("请重新输入");
				System.err.println("两个素数：");
				i=1;
			}
		}
		n = p*q;
		d = d(p,q,e);
		i=1;
		System.out.print("输入 要加密/加密的数组，用空格或逗号隔开:");
		while(i==1){
			int y=0;
			tmp = br.readLine();
			String[] str=null;
			if(tmp.contains(" ")){
				str = tmp.split(" ");
			}
			else if(tmp.contains(",")){
				str = tmp.split(",");
			}
			else if(!tmp.contains(",")&&!tmp.contains(" ")){//判断数组是否有特殊字符
				for(int v=0;v<tmp.length();v++){
					if(!Character.isDigit(tmp.charAt(v))){
						y=1;
						break;
					}
				}
			}
			if(y==1) {System.out.print("你输入的数组有除了逗号和空格之外的特殊符号，重新输入加密数组:");continue;}
			if(jug(str,n)==2) continue;
			System.out.println("选择加密解密的方式：1.加密        2.解密");
			int w = sc.nextInt();
			if(w==2){
				System.out.println("解密结果是："+M(str,d,n));
			}
			else{
				System.out.println("不选择或者选择了1的方式为加密");
				System.out.println("加密结果是："+C(str,n,e));
			}
		}
	}
}
