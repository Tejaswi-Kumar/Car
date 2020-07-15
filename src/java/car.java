/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tejpa
 */
public class car  {
    String mod,type,fuel;
    int eng,seat;
    double mil,score,price;
    public car(String mod,double price,int eng,String fuel,double mil,String type,int seat,double score){
        this.mod=mod;
        this.price=price;
        this.mil=mil;
        this.eng=eng;
        this.fuel=fuel;
        this.score=score;
        this.type=type;
        this.seat=seat;
    }
    public double getscore() {
		return score;
	}
    public String getmod(){
        return mod;
    }
    public int geteng(){
        return eng;
    }
    public String getfuel(){
        return fuel;
    }
    public double getprice(){
        return price;
    }
    public double getmil(){
        return mil;
    }	
}

