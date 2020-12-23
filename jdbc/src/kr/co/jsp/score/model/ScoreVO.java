package kr.co.jsp.score.model;

public class ScoreVO {
	private long id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double average;
	
	public ScoreVO() {}
	
	public ScoreVO(long id, String name, int kor, int eng, int math, int total, double average) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = total;
		this.average = average;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = this.eng+this.kor+this.math;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage() {
		this.average = this.total/3.0;
	}
}
