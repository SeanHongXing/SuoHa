package com.training.chapter6;

import java.util.Scanner;

/**
 * @author:      hongxing
 * @date:        2018年11月8日 下午1:12:18
 * @description: 游戏桌类
 */
public class GameTable {
	//房间倍率
	private final int ratio;
	//对战过程中分数池
	private int scoresPool = 0;
	//发牌轮次
	private int turns = 0;
	//玩家的数量、玩家对象数组
	private int playerSites;
	private Player[] players;
	//机器人的数量、机器人对象数组
	private int comPlayerSites;
	private ComPlayer[] comPlayers;
	//总玩家数量
	private  int totalPlayersSites;
	//牌桌上剩余的牌
	private int[] leftCards = new int[52];
	//牌桌上剩余牌的数量（初始情况下，有52张牌，每发一张牌，牌堆数量减一）
	private int leftCardsCounts = 52;
	//识别牌的数组
	/** 牌的花色数组 */
	private final static String[] cardColors = {"♠","♣","♥","♦"};
	/** 牌的数值数组 */
	private final static String[] cardValues = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	//机器人随机姓名、性别数组
	/** 机器人姓名数组 */
	private final static String[] playerNames = {"王会才","陈康壮","杨震世","高奇林","杨思航","郭东炎","吴松波","马伯会","杨彪","吴福彬","赵子富"};
	/** 性别数组 */
	private final static String[] playerGenders = {"男","女"};
	
	
	
	public GameTable(int playerSites, int comPlayerSites, int money, int ratio) {
		this.ratio = ratio;							//设置房间倍率
		setPlyaerSites(playerSites);				//设置牌桌上玩家的数量
		setComPlayerSites(comPlayerSites);  		//设置牌桌上机器人的数量
		this.totalPlayersSites = this.playerSites + this.comPlayerSites;
		initPlayers(money);							//初始化所有玩家（包括机器人）
		showPlayerInfo();							//展示所有玩家信息
		System.out.println("*****生成牌堆中*****");
		initLeftCards();							//初始化牌堆
		showLeftCards();							//展示牌堆
		System.out.println("*****对局即将开始*****");	//打印初始化完成标志
	}
	
	
	private void setScoresPool(int scores) {
		this.scoresPool = scores;
	}
	
	private int getScoresPool() {
		return this.scoresPool;
	}
	
	
	private void setComPlayerSites(int sites) {
		if(sites <= 0) {
			System.out.println("请输入合法的机器人数量！");
			return;
		}
		this.comPlayerSites = sites;
	}
	
	public int getComPlayerSites() {
		return this.comPlayerSites;
	}
	
	private void setPlyaerSites(int sites) {
		if(sites < 0) {
			System.out.println("请输入合法的玩家数量！");
			return;
		}
		this.playerSites = sites;
	}
	
	public int getPlyaerSites() {
		return this.playerSites;
	}
	
	/**
	 * @description: 拿出一副新牌，先按顺序排放，随后进行洗牌
	 */
	private void initLeftCards() {

		for(int i = 0; i < 52; i++)
			leftCards[i] = i;
		shuffleCards(leftCards);
	}
	
	/**
	 * @description: 对剩余牌堆的牌进行洗牌操作
	 * @param cards:剩余牌堆
	 */
	private void shuffleCards(int[] cards) {
		for(int i = leftCardsCounts - 1; i >= 0 ; i--) {
			int index = (int)(Math.random() * 1000) % leftCardsCounts;
			int temp = cards[index];
			cards[index] = cards[i];
			cards[i] = temp;
		}
	}
	
	/**
	 * @description: 将0-51之间的数字，转换为对应的牌
	 * @param card：牌的编号
	 * @return：牌的字符串描述
	 */
	public static String getCardString(int card) {
		String str = "";
		if(card >= 0 && card <= 51) {
			str += cardColors[card / 13];
			str += cardValues[card % 13];
		}else {
			return "错误的牌号";
		}
		return str;
	}

	/**
	 * @description: 展示剩余牌堆
	 */
	public void showLeftCards() {
		System.out.println("========当前牌桌牌堆=======");
		for(int i = leftCardsCounts - 1; i >= 0; i--) {
			System.out.printf("%s\t", getCardString(leftCards[i]));
			if( i % (int)(leftCardsCounts / 4) == 0) {
				System.out.println();
			}
		}
	}
	
	/**
	 * @description: 对玩家进行初始化操作，包括金额、姓名、性别
	 * @param money：玩家初始的金额
	 */
	private void initPlayers(int money) {
		System.out.println("*****生成玩家中*****");
		players = new Player[playerSites];
		comPlayers = new ComPlayer[comPlayerSites];
		for(int i = 0; i < playerSites; i++) {
			String randName = playerNames[(int)(Math.random() * 100) % playerNames.length];
			String randGender = playerGenders[(int)(Math.random() * 100) % playerGenders.length];
			players[i] = new Player(randName,randGender,money);	
		}
		for(int i = 0; i < comPlayerSites; i++) {
			String randName = playerNames[(int)(Math.random() * 100) % playerNames.length];
			String randGender = playerGenders[(int)(Math.random() * 100) % playerGenders.length];
			comPlayers[i] = new ComPlayer(randName,randGender,money);
		}
		
	}
	
	/**
	 * @description: 打印所有对战玩家的信息
	 */
	private void showPlayerInfo() {
		System.out.println("==========当前对局所有玩家信息==========");
		for(int i = 0 ; i < playerSites; i++) {
			players[i].showInfo();
		}
		for(int i = 0; i < comPlayerSites; i++) {
			comPlayers[i].showInfo();
		}
	}

	/**
	 * @description: 不展示底牌
	 */
	private void showPlayerCards() {
		System.out.println("==========当前对局所有玩家手牌==========");
		for(int i = 0 ; i < playerSites; i++) {
			players[i].showCards();
		}
		for(int i = 0; i < comPlayerSites; i++) {
			comPlayers[i].showCards();
		}
	}

	/**
	 * @description: 展示所有手牌
	 * @param a
	 */
	private void showPlayerCards(int a) {
		System.out.println("==========当前对局所有玩家手牌==========");
		for(int i = 0 ; i < playerSites; i++) {
			players[i].showCards();
		}
		for(int i = 0; i < comPlayerSites; i++) {
			comPlayers[i].showCards(a);
		}
	}
	
	/**
	 * @description: 重置牌桌的当前对局信息
	 */
	private void resetGameStatus() {
		for(int i = 0 ; i < playerSites; i++) {
			players[i].resetStatus();
		}
		for(int i = 0; i < comPlayerSites; i++) {
			comPlayers[i].resetStatus();
		}
		this.leftCardsCounts = 52;		//重置牌桌信息
		initLeftCards();				//重新洗牌
		setScoresPool(0);				//重置对局分数池
		this.turns = 0; 				//重置发牌轮次
	}
	
	/**
	 * @description: 向牌桌上所有未弃牌的玩家发一张牌，并将对应玩家的分数添加到分数池中
	 */
	private void dealCard() {
		int turnMoney = (int) (this.turns * this.ratio / 1.5);
		for(int i = 0; i < playerSites; i++) {
			if(players[i].isDiscard) 
				continue;
			else {
				players[i].money -= turnMoney;
				setScoresPool(getScoresPool() + turnMoney);
				players[i].getCard(leftCards[leftCardsCounts-- - 1]);
			}
				
		}
		for(int i = 0; i < comPlayerSites; i++) {
			if(comPlayers[i].isDiscard) 
				continue;
			else {
				comPlayers[i].money -= turnMoney;
				setScoresPool(getScoresPool() + turnMoney);
				comPlayers[i].getCard(leftCards[leftCardsCounts-- - 1]);
			}
		}
		this.turns++;
	}

	
	/**
	 * @description: 比赛胜负判断
	 */
	private void gameJudge() {
		Player player = players[0];
		Player winner = players[0];
		//判断对局胜利玩家
		for(int i = 0; i < totalPlayersSites; i++) {
			if(i < this.playerSites) {
				player = players[i];
			}else {
				player = comPlayers[i - playerSites];
			}
			winner = cardsCompare(winner,player);
		}
		//对胜利玩家进行加分操作，并公布对局结果
		winner.money += this.scoresPool;
		System.out.printf("========对局战报=======\n胜利玩家：%s\t获得分数：%d\t结束后分数：%d\n", winner.name,this.scoresPool,winner.money);
	}


	/**
	 * @description: 判断玩家1与玩家2的胜利者
	 * @param player1	第一个玩家对象
	 * @param player2	第二个玩家对象
	 * @return	返回胜利的玩家对象;
	 */
	private Player cardsCompare(Player player1,Player player2) {
		
		Player winner = player1;
		if(player1.handCardsCounts == 5 && player2.handCardsCounts == 5) {
			if(player1.calcCardsLevel() > player2.calcCardsLevel())
				winner = player1;
			else if(player1.calcCardsLevel() == player2.calcCardsLevel()) {
				////////////牌面等级相同的情况下，判断大小，简单方法：判断最大的值
				player1.sortCards();
				player2.sortCards();
				for(int i = 0; i < 5; i++) {
					if(player1.handCards[i] % 13 == player2.handCards[i] % 13) {
						if(i != 4)
							continue;
						else {
							for(int j = 0; j < 5; j++) {
								if(player1.handCards[j] / 13 < player2.handCards[j] / 13) {
									winner = player1;
									break;
								}else if(player1.handCards[j] / 13 > player2.handCards[j] / 13) {
									winner = player2;
									break;
								}else
									continue;
							}
						}
					}else if(player1.handCards[i] % 13 != 0 && player2.handCards[i] % 13 == 0) {
						winner = player2;
						break;
					}else if(player1.handCards[i] % 13 != 0 && player2.handCards[i] % 13 != 0 && player1.handCards[i] % 13 < player2.handCards[i] % 13) {
						winner = player2;
						break;
					}else {
						winner = player1;
						break;
					}
				}
			}else
					winner = player1;
		}else {
			if(player1.handCardsCounts < player2.handCardsCounts)
				winner = player2;
			else
				winner = player1;
		}
		return winner;
	}
	
	
	/**
	 * @description: 确认玩家是否继续游戏
	 */
	private void continueConfirm() {
		System.out.println("是否继续对战？（y/n）");			//向玩家确认是否继续游戏
		if(new Scanner(System.in).next().equals("n")) {
			System.out.print("感谢您的参与！");
			System.exit(0);
		}
	}
	
	/**
	 * @description: 开始游戏循环
	 */
	public void startGame() {
		while(true) {
			dealCard();
			dealCard();			//进行两次发牌操作
			showPlayerCards();	//展示所有玩家的手牌
			int dealTimes = 2;
			//继续循环三次发牌
			while(dealTimes < 5) {
				if(!players[0].isDiscard) {
					System.out.println("是否跟注？（y/n）");
					String choice = new Scanner(System.in).next();
					if(choice.equalsIgnoreCase("n")) 
						players[0].isDiscard = true;
				}
				dealCard();
				showPlayerCards();			//展示所有玩家的手牌
				dealTimes++;
			}
			showPlayerCards(1);				//展示所有玩家所有牌
			//当前对局结束，进行胜负判断，并且改变分数
			gameJudge();
			resetGameStatus();				//重置牌桌对局信息
			showPlayerInfo();				//展示玩家状态信息
			continueConfirm();				//确认玩家是否继续游戏
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
			System.out.println("请输入机器人的数量(1~8)：");
			int num1 = new Scanner(System.in).nextInt();
			System.out.println("请输入初始分数：");
			int num2 = new Scanner(System.in).nextInt();
			System.out.println("请输入房间倍率：");
			int num3 = new Scanner(System.in).nextInt();
			GameTable gt = new GameTable(1,num1,num2,num3);
			gt.startGame();
	}

}
