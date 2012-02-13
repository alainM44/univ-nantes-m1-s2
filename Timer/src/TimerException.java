public class TimerException extends Exception {

    private String msg;

    public  TimerException(String msg) {
	this.msg = msg;
    }            

    public String toString() {
        return msg + " : " + super.toString();
    }

}