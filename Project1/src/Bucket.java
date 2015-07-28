/**
 * Created by Meghan Dwyer and Bob Krency on 7/12/2015.
 */

/**
 * Bucket class that will be utilized in the Sand class
 */
public class Bucket {

    /**
     * Represents how much sand it is possible for the bucket to hold
     */
    private int capacity;

    /**
     * Represents how much sand the bucket currently holds
     */
    private int fill;

    /**
     * Bucket constructor
     * @param bucketCapactiy - how much it is possible for a bucket to hold
     */
    public Bucket(int bucketCapactiy){
        this.capacity = bucketCapactiy;
        this.fill = 0;
    } // constructor

    /**
     * the capacity of the current bucket
     * @return - integer representing how much sand a bucket can hold
     */
    public int getCapacity(){
        return this.capacity;
    } // getCapacity

    /**
     * the fill of the current bucket
     * @return - integer representing how much sand a bucket currently holds
     */
    public int getFill(){
        return this.fill;
    } // getFill

    /**
     * fill the bucket to capacity
     */
    public void fillBucket(){
        this.fill = this.capacity;
    } //fillBucket

    /**
     * fill current bucket with sand in another bucket(b), if bucket b contains
     * more sand than the current bucket can hold fill the current bucket
     *
     * adjust the amount of sand in bucket b accordingly
     * @param b - a bucket object
     */
    public void fillBucket(Bucket b){
        int fillAmount = this.getCapacity() - this.getFill();

        if (b.getFill() < fillAmount){
            fillAmount = b.getFill();
        } // if

        b.emptyBucket(fillAmount);

        this.fill += fillAmount;
    } //fillBucket

    /**
     * fill a bucket with a specified amount
     * @param amount - amount of sand to put in the current bucket
     */
    public void fillBucket(int amount){
        this.fill += amount;
    }

    /**
     * create a copy of the current bucket
     * @return - the copy of the current bucket
     */
    public Bucket clone(){
        Bucket b = new Bucket(this.getCapacity());
        b.fillBucket(this.getFill());
        return b;
    }

    /**
     * empty the current bucket of all sand
     */
    public void emptyBucket(){
        this.fill = 0;
    } //emptyBucket

    /**
     * empty the current bucket of a specified amount of sand
     * @param amount - the amount of sand to be removed from the current bucket
     */
    public void emptyBucket(int amount){
        this. fill -= amount;
    } // emptyBucket
} // Bucket
