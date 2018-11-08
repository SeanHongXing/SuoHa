package com.training.chapter6;

/**
 * @author:      hongxing
 * @date:        2018年11月8日 下午1:12:47
 * @description: 机器人类，继承自玩家类
 */
public class ComPlayer extends Player{

	public ComPlayer(String name, String gender, int money) {
		super(name, gender, money);
		// TODO Auto-generated constructor stub
	}

	
	
	


	/* (non-Javadoc)
	 * @see com.training.chapter6.Player#showCards()
	 * desc:重写机器人的展示牌堆方法——隐藏第一张牌
	 */
	public void showCards() {
		System.out.print(this.name + "\t" + this.money + '\t');
		for(int i = 0; i < this.handCardsCounts; i++) {
			if(i == 0)
				System.out.print("■\t");
			else
				System.out.print(GameTable.getCardString(this.handCards[i]) + "\t");
		}
		System.out.println();
	}

	/**
	 * @description: 重载方法，机器人展示底牌
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
