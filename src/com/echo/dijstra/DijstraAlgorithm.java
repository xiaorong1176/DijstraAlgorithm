package com.echo.dijstra;

/**
 * �Ͽ�˹���㷨
 * ���ڹ������������ʹ�����ȶ��д洢������Ϣ
 * α���룺
	 1  function Dijkstra(G, w, s)
	 2     for each vertex v in V[G]    // ��ʼ��
	 3           d[v] := infinity       // �����c����֪��̾��x���O�ɟo�F��
	 4           previous[v] := undefined  // �������֪���·���ϵ�ǰ����δ֪
	 	   // ��Ϊ�����㵽������䲻���ƶ��κξ��룬���Կ���ֱ�ӽ�s��s����С������Ϊ0
	 5     d[s] := 0                       
	 6     S := empty set
	 7     Q := set of all vertices
	 8     while Q is not an empty set     // Dijkstra���㷨���w
	 9           u := Extract_Min(Q)
	10           S.append(u)
	11           for each edge outgoing from u as (u,v)
	12                  if d[v] > d[u] + w(u,v)    // ��չ�ߣ�u,v����w(u,v)Ϊ��u��v��·�����ȡ�
	13                        d[v] := d[u] + w(u,v)// ����·�����ȵ���С���Ǹ���ֵ��
	14                        previous[v] := u     // �o�ǰڅ��c
 
 * @author wly
 *
 */
public class DijstraAlgorithm {
	
	private static int IMAX = 10000; //����ͨ״̬
	public static int[][] adjMat = {
		{0,10,3,IMAX},
		{IMAX,0,4,5},
		{IMAX,4,0,10},
		{IMAX,IMAX,IMAX,0}
	};
	
	public static void main(String[] args) {
		DijstraAlgorithm dijstraAlgorithm = new DijstraAlgorithm();
		int start = 2;
		int end = 3;
		System.out.println("------����------");
		System.out.println("\n��" + start + "��" + end 
				+ "�ľ�����:" + dijstraAlgorithm.reslove(adjMat, start, end));
		
		System.out.println("------����------");
		start = 0;
		end = 3;
		System.out.println("\n��" + start + "��" + end 
				+ "�ľ�����:" + dijstraAlgorithm.reslove(adjMat, start, end));
	}
	
	/**
	 * �����ڽӾ���adjMat��ʾ��ͼ�У����ӵ�s����t�����·��
	 * @param adjMat �ڽӾ���
	 * @param s ���
	 * @param t �յ�
	 * @return
	 */
	public int reslove(int[][] adjMat,int s,int t) {
		
		//�жϲ����Ƿ���ȷ
		if(s < 0 || t < 0 || s >=adjMat.length || t >= adjMat.length) {
			System.out.println("���󣬶��㲻��ͼ��!");
			return IMAX;
		}
		
		//������¼ĳ�������Ƿ��Ѿ���ɱ�������������ȶ��е�"�Ƴ�����"����
		boolean[] isVisited = new boolean[adjMat.length];
		//���ڴ洢��s��������֮������·������
		int[] d = new int[adjMat.length]; 
		
		//��ʼ������
		for(int i=0;i<adjMat.length;i++) {
			isVisited[i] = false;
			d[i] = IMAX;
		}
		d[s] = 0; //s��s�ľ�����0 
		isVisited[s] = true; //��s���Ϊ�ѷ��ʹ���

		//��δ�����Ķ�����Ŀ��������ȶ����Ƿ�Ϊ�յ��жϼ�Queue.isEmpty()
		int unVisitedNum = adjMat.length;
		//���ڱ�ʾ��ǰ���������·����Ȩ����С�Ķ��������,��Ӧ���ȶ�����,Ĭ�������
		int index = s; 
		while(unVisitedNum > 0 && index != t) {
			int min = IMAX;
			for(int i=0;i<adjMat.length;i++) { //ȡ����i�е���СԪ�ص�����
				if(min > d[i] && !isVisited[i]) {
					min = d[i];
					index = i;
				}
			}
			if(index == t || unVisitedNum == 0) {
				System.out.print(index); //��ӡ���·��
			} else {
				System.out.print(index + "=>"); //��ӡ���·��
			}
			for(int i=0;i<adjMat.length;i++) {
				if(d[index] + adjMat[index][i] < d[i]) {
					d[i] = d[index] + adjMat[index][i];
				}
			}
			unVisitedNum --;
			isVisited[index] = true;
		}
		return d[t];
	}
}
