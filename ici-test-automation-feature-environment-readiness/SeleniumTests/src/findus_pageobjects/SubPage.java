package findus_pageobjects;

public abstract class SubPage<TParent> {

    private TParent parentPage;

    public TParent getParentPage() {
        return this.parentPage;
    }

    protected SubPage(TParent parentPage){
        if(parentPage == null)
            throw new IllegalArgumentException("parentPage cannot be null");

        this.parentPage = parentPage;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
