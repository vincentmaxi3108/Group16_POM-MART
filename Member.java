public class Member {

    private String idMember;
    private int poin;

    // constructor #1
    public Member(String id, int p) {
        idMember = id;
        poin = p;
    }

    // constructor #2
    public Member() {
        idMember = "";
        poin = 0;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public int getPoin() {
        return poin;
    }

    // menambah poin
    public void tambahPoin(int poin) {
        this.poin += poin;
    }
}