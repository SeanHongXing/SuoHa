package com.training.chapter6;

import java.util.Arrays;

/**
 * @author:      hongxing
 * @date:        2018年11月8日 下午1:12:37
 * @description: 玩家类
 */
public class Player {
	//玩家姓名
	protected String name;
	//玩家性别
	protected String gender;
	//玩家分数
	protected int money;
	//玩家手牌数组
	protected int[] handCards = new int[5];
	//玩家当前手牌数量
	protected int handCardsCounts = 0;
	//当前游戏玩家是否弃牌标志
	protected boolean isDiscard = false;
	//玩家牌面等级
	protected int cardsLevel = 0;
	//"单张" = 0 "一对" = 1 "两对" = 2"三条" = 3"顺子" = 4"同花" = 5"葫芦" = 6"四条" = 7"同花顺"= 8
	
	/**
	 * @description: 重置玩家手牌、手牌计数、弃牌信息
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
	 * @description: 玩家增加一张手牌
	 * @param card
	 */
	public void getCard(int card) {
		this.handCards[this.handCardsCounts] = card;
		this.handCardsCounts++;//手牌数量+1
	}
	
	/**
	 * @description: 玩家手牌展示
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
	 * @description: 将手牌按照从大到小进行整理
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
	 * @description: 根据牌面类型返回牌的等级
	 * @return	返回-1表示牌面存在问题
	 */
	public int calcCardsLevel() {
		if(this.getCardsLevel().equalsIgnoreCase("单张"))
			return 0;
		else if(this.getCardsLevel().equalsIgnoreCase("一对"))
			return 1;
		else if(this.getCardsLevel().equalsIgnoreCase("两对"))
			return 2;
		else if(this.getCardsLevel().equalsIgnoreCase("三条"))
			return 3;
		else if(this.getCardsLevel().equalsIgnoreCase("顺子"))
			return 4;
		else if(this.getCardsLevel().equalsIgnoreCase("同花"))
			return 5;
		else if(this.getCardsLevel().equalsIgnoreCase("葫芦"))
			return 6;
		else if(this.getCardsLevel().equalsIgnoreCase("四条"))
			return 7;
		else if(this.getCardsLevel().equalsIgnoreCase("同花顺子"))
			return 8;
		return -1;
	}
	
	/**
	 * @description: 展示玩家基本信息
	 */
	public void showInfo() {
		System.out.printf("玩家昵称：%s\t玩家性别：%s\t玩家分数：%d\n",this.name,this.gender,this.money);
	}
	
	
	
	
	
	/**
	 * @description: 获得牌面类型"同花顺","四条","葫芦","同花","顺子","三条","两对","一对","单张"
	 * @return
	 */
	public String getCardsLevel() {
		String str = "";
		//判断同花
		for(int i = 0; i < handCardsCounts - 1; i++) {
			if(this.handCards[i] / 13 == this.handCards[i + 1] / 13)
				if(i != handCardsCounts - 2)
					continue;
				else
					str += "同花";
			else
				break;
		}
						
		//判断顺子
		int[] copyCards = new int[5];
		for(int i = 0; i < handCardsCounts; i++)
			copyCards[i] = this.handCards[i] % 13;
		Arrays.sort(copyCards);
		for(int i = 0; i < handCardsCounts - 1; i++) {
			if(copyCards[i] == copyCards[i + 1] - 1)
				if(i != handCardsCounts - 2)
					continue;
				else
					str += "顺子";
			else
				break;
		}
		if(copyCards[0] == 0)
			if(copyCards[1] == 10)
				if(copyCards[2] == 11)
					if(copyCards[3] == 12)
						if(copyCards[4] == 13)
							str += "顺子";
		//判断对子
		int equalNums = 0;
		for(int i = 0; i < handCardsCounts; i++) {
			for(int j = i + 1; j < handCardsCounts; j++) {
				if(this.handCards[i] % 13 == this.handCards[j] % 13)
					equalNums++;
			}
		}
		if(equalNums == 1)
			str += "一对";
		if(equalNums == 2)
			str += "两对";
		if(equalNums == 3)
			str += "三条";
		if(equalNums == 4)
			str += "葫芦";
		if(equalNums == 6)
			str += "四条";
		
		if("".equalsIgnoreCase(str))
			str += "单张";
		return str;
	}
	
	
}
