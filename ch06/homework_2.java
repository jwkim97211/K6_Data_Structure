package ch06;

class Polynomial implements Comparable<Polynomial> {
	double coef; // 계수
	int exp; // 지수

	public Polynomial(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}

	@Override
	public int compareTo(Polynomial o) {
		if (this.exp > o.exp)
			return 1;
		else if (this.exp < o.exp)
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return coef + "X^" + exp;
	}
}

public class homework_2 {

	static void merge(Polynomial[] a, int lefta, int righta, int leftb, int rightb) {
		Polynomial[] temp = new Polynomial[a.length];
		int idx = 0;
		int p = lefta, q = leftb;
		while (p <= righta && q <= rightb) {
			if (a[p].compareTo(a[q]) == -1)
				temp[idx++] = a[p++];
			else if (a[p].compareTo(a[q]) == 1)
				temp[idx++] = a[q++];
			else {
				temp[idx++] = a[p++];
				temp[idx++] = a[q++];
			}
		}
		while (p > righta && q <= rightb)
			temp[idx++] = a[q++];
		while (q > rightb && p <= righta)
			temp[idx++] = a[p++];
		for (int j = 0; j < idx; j++)
			a[lefta + j] = temp[j];
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);
		merge(a, left, mid, mid + 1, right);
		return;
	}

	public static void main(String[] args) {
		Polynomial[] x = { new Polynomial(1.5, 3), 
						   new Polynomial(2.5, 7), 
						   new Polynomial(3.3, 2),
					 	   new Polynomial(4.0, 1), 
						   new Polynomial(2.2, 0), 
						   new Polynomial(3.1, 4), 
						   new Polynomial(3.8, 5), 
						   };
		Polynomial[] y = { new Polynomial(1.5, 1), 
				           new Polynomial(2.5, 2), 
						   new Polynomial(3.3, 3),
						   new Polynomial(4.0, 0), 
						   new Polynomial(2.2, 4), 
						   new Polynomial(3.1, 5), 
						   new Polynomial(3.8, 6), 
						   };
		int nx = x.length;
		
		System.out.println("x");
		ShowPolynomial(x);
		System.out.println("y");
		ShowPolynomial(y);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		System.out.println("x");
		ShowPolynomial(x);
		System.out.println("y");
		ShowPolynomial(y);
		Polynomial[] z = new Polynomial[8];
		AddPolynomial(x, y, z);// 다항식 덧셈 z = x + y
		System.out.println("x+y");
		ShowPolynomial(z);
		Polynomial[] z1 = new Polynomial[14];
		MultiplyPolynomial(x, y, z1);// 다항식 곱셈 z = x * y
		System.out.println("x*y");
		ShowPolynomial(z1);
		int result = EvaluatePolynomial(z, 10);// 다항식 값 계산 함수 z(10) 값 계산한다
		System.out.println(" result = " + result);
	}

	static int EvaluatePolynomial(Polynomial[] z, int i) {
		int result=0;
		for(int j=0;j<z.length;j++)
			result+=z[j].coef*Math.pow(i, z[j].exp);
		return result;
	}

	static void MultiplyPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int idx=0;
		Polynomial[] z1 = new Polynomial[49];
		for(int i=0;i<x.length;i++) {
			for(int j=0;j<y.length;j++) {
				z1[idx++]=new Polynomial(x[i].coef * y[j].coef, x[i].exp+y[j].exp);
			}
		}
		MergeSort(z1, 0, idx - 1);
	    for (int i = 0; i < idx - 1; i++) {
	        if (z1[i].exp == z1[i + 1].exp) {
	            z1[i].coef += z1[i + 1].coef;
	            for (int j = i + 1; j < idx - 1; j++) {
	                z1[j] = z1[j + 1];
	            }
	            idx--;
	            i--;
	            z1[idx]=null;
	        }
	    }
	    for(int i=0;i<z.length;i++)
	    	z[i]=z1[i];
	}

	static void AddPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < x.length && j < y.length) {
			if (x[i].exp == y[j].exp) {
				z[k++] = new Polynomial(x[i].coef + y[j].coef, x[i].exp);
				i++;
				j++;
			} else if (x[i].exp > y[j].exp) {
				z[k++] = new Polynomial(x[i].coef, x[i].exp);
				i++;
			} else {
				z[k++] = new Polynomial(y[j].coef, y[j].exp);
				j++;
			}
		}

		while (i < x.length) {
			z[k++] = new Polynomial(x[i].coef, x[i].exp);
			i++;
		}

		while (j < y.length) {
			z[k++] = new Polynomial(y[j].coef, y[j].exp);
			j++;
		}
	}

	static void ShowPolynomial(Polynomial[] x) {
		for (int i = 0; i < x.length; i++)
			System.out.println(x[i].toString());
	}
}
