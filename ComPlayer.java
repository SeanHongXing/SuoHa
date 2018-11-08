package com.training.chapter6;

/**
 * @author:      hongxing
 * @date:        2018��11��8�� ����1:12:47
 * @description: �������࣬�̳��������
 */
public class ComPlayer extends Player{

	public ComPlayer(String name, String gender, int money) {
		super(name, gender, money);
		// TODO Auto-generated constructor stub
	}

	
	
	


	/* (non-Javadoc)
	 * @see com.training.chapter6.Player#showCards()
	 * desc:��д�����˵�չʾ�ƶѷ����������ص�һ����
	 */
	public void showCards() {
		System.out.print(this.name + "\t" + this.money + '\t');
		for(int i = 0; i < this.handCardsCounts; i++) {
			if(i == 0)
				System.out.print("��\t");
			else
				System.out.print(GameTable.getCardString(this.handCards[i]) + "\t");
		}
		System.out.println();
	}

	/**
	 * @description: ���ط�����������չʾ����
	 * @param a
	 */
	public void showCards(int a) {
		this.sortCards();
		System.out.print(this.name + "\t" + this.money + '\t');
		for(int i = 0; i < this.handCardsCounts; i++) {
			System.out.print(GameTable.getCardString(this.handCards[i]) + "\t");
		}
		System.out.println(this.getCardsLevel());
	}
	
	
}
