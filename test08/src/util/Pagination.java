package util;

public class Pagination {
	private int ye;
	private int maxYe;
	private int beginYe;
	private int endYe;
	private int begin;

	// numInPage一页显示多少条数据 ，numOfPage一页显示多少条页码
	public Pagination(int ye, int count, int numInPage, int numOfPage) {
		this.ye = ye;
		begin = (this.ye - 1) * numInPage;
		if (this.ye <= 1) {
			this.ye = 1;
		}
		maxYe = count % numInPage == 0 ? count / numInPage : count / numInPage + 1;
		if (this.ye >= maxYe) {
			this.ye = maxYe;
		}

	    beginYe = this.ye - numOfPage / 2;
		if (beginYe <= 1) {
			beginYe = 1;
		}
		endYe = beginYe + numOfPage - 1;
		if (endYe >= maxYe) {
			endYe = maxYe;
		}
	}

	public int getBegin() {	
		return begin;
	}
	public int getYe() {
		return ye;
	}

	public int getMaxYe() {
		return maxYe;
	}

	public int getBeginYe() {
		return beginYe;
	}

	public int getEndYe() {
		return endYe;
	}

}

