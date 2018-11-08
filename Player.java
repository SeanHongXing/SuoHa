package com.training.chapter6;

import java.util.Arrays;

/**
 * @author:      hongxing
 * @date:        2018��11��8�� ����1:12:37
 * @description: �����
 */
public class Player {
	//�������
	protected String name;
	//����Ա�
	protected String gender;
	//��ҷ���
	protected int money;
	//�����������
	protected int[] handCards = new int[5];
	//��ҵ�ǰ��������
	protected int handCardsCounts = 0;
	//��ǰ��Ϸ����Ƿ����Ʊ�־
	protected boolean isDiscard = false;
	//�������ȼ�
	protected int cardsLevel = 0;
	//"����" = 0 "һ��" = 1 "����" = 2"����" = 3"˳��" = 4"ͬ��" = 5"��«" = 6"����" = 7"ͬ��˳"= 8
	
	/**
	 * @description: ����������ơ����Ƽ�����������Ϣ
	 */
	public void resetStatus() {
		this.handCardsCounts = 0;
		this.isDiscard = false;
		for(int i = 0; i < 5; i++)
			handCards[i] = -1;
	}
	
	public Player(String name,String gender,int money) {
		this.name = name;
		this.gender = gender;
		this.money = money;
		resetStatus();
	}
	
	
	/**
	 * @description: �������һ������
	 * @param card
	 */
	public void getCard(int card) {
		this.handCards[this.handCardsCounts] = card;
		this.handCardsCounts++;//��������+1
	}
	
	/**
	 * @description: �������չʾ
	 */
	public void showCards() {
		this.sortCards();
		System.out.print(this.name + "\t" + this.money + '\t');
		for(int i = 0; i < this.handCardsCounts; i++) {
			System.out.print(GameTable.getCardString(this.handCards[i]) + "\t");
		}
		System.out.println(this.getCardsLevel());
	}
	
	/**
	 * @description: �����ư��մӴ�С��������
	 */
	public void sortCards() {
		for(int i = 0; i < this.handCardsCounts; i++) {
			int maxIndex = i;
			for(int j = i; j < this.handCardsCounts; j++) {
				if(this.handCards[j] % 13 == 0)
					maxIndex = j;
				if(this.handCards[j] % 13 != 0 && this.handCards[maxIndex] % 13 != 0 && this.handCards[j] % 13 > this.handCards[maxIndex] % 13)
					maxIndex = j;
			}
			int temp = this.handCards[i];
			this.handCards[i] = this.handCards[maxIndex];
			this.handCards[maxIndex] = temp;
		}
	}
	
	/**
	 * @description: �����������ͷ����Ƶĵȼ�
	 * @return	����-1��ʾ�����������
	 */
	public int calcCardsLevel() {
		if(this.getCardsLevel().equalsIgnoreCase("����"))
			return 0;
		else if(this.getCardsLevel().equalsIgnoreCase("һ��"))
			return 1;
		else if(this.getCardsLevel().equalsIgnoreCase("����"))
			return 2;
		else if(this.getCardsLevel().equalsIgnoreCase("����"))
			return 3;
		else if(this.getCardsLevel().equalsIgnoreCase("˳��"))
			return 4;
		else if(this.getCardsLevel().equalsIgnoreCase("ͬ��"))
			return 5;
		else if(this.getCardsLevel().equalsIgnoreCase("��«"))
			return 6;
		else if(this.getCardsLevel().equalsIgnoreCase("����"))
			return 7;
		else if(this.getCardsLevel().equalsIgnoreCase("ͬ��˳��"))
			return 8;
		return -1;
	}
	
	/**
	 * @description: չʾ��һ�����Ϣ
	 */
	public void showInfo() {
		System.out.printf("����ǳƣ�%s\t����Ա�%s\t��ҷ�����%d\n",this.name,this.gender,this.money);
	}
	
	
	
	
	
	/**
	 * @description: �����������"ͬ��˳","����","��«","ͬ��","˳��","����","����","һ��","����"
	 * @return
	 */
	public String getCardsLevel() {
		String str = "";
		//�ж�ͬ��
		for(int i = 0; i < handCardsCounts - 1; i++) {
			if(this.handCards[i] / 13 == this.handCards[i + 1] / 13)
				if(i != handCardsCounts - 2)
					continue;
				else
					str += "ͬ��";
			else
				break;
		}
						
		//�ж�˳��
		int[] copyCards = new int[5];
		for(int i = 0; i < handCardsCounts; i++)
			copyCards[i] = this.handCards[i] % 13;
		Arrays.sort(copyCards);
		for(int i = 0; i < handCardsCounts - 1; i++) {
			if(copyCards[i] == copyCards[i + 1] - 1)
				if(i != handCardsCounts - 2)
					continue;
				else
					str += "˳��";
			else
				break;
		}
		if(copyCards[0] == 0)
			if(copyCards[1] == 10)
				if(copyCards[2] == 11)
					if(copyCards[3] == 12)
						if(copyCards[4] == 13)
							str += "˳��";
		//�ж϶���
		int equalNums = 0;
		for(int i = 0; i < handCardsCounts; i++) {
			for(int j = i + 1; j < handCardsCounts; j++) {
				if(this.handCards[i] % 13 == this.handCards[j] % 13)
					equalNums++;
			}
		}
		if(equalNums == 1)
			str += "һ��";
		if(equalNums == 2)
			str += "����";
		if(equalNums == 3)
			str += "����";
		if(equalNums == 4)
			str += "��«";
		if(equalNums == 6)
			str += "����";
		
		if("".equalsIgnoreCase(str))
			str += "����";
		return str;
	}
	
	
}
