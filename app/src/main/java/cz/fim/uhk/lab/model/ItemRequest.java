/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.model;

/*
   Class ItemRequest is a custom class used as a shell for a request for a quantity of a specific item.
 */

public class ItemRequest {
    private long requestedId;
    private int requestedQuantity;

    public ItemRequest() {
    }

    public long getRequestedId() {
        return requestedId;
    }

    public void setRequestedId(long requestedId) {
        this.requestedId = requestedId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    @Override
    public String toString() {
        return "CheckoutRequest{" +
                "requestedItemId=" + requestedId +
                ", requestedQuantity=" + requestedQuantity +
                '}';
    }
}
