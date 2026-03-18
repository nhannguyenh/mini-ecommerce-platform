import axios from "axios";
import {useEffect, useState} from "react";
import "./CheckoutPage.css";

function CheckoutPage({setError}) {
    const [cart, setCart] = useState([]);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            try {
                axios.get("http://localhost:8080/api/carts", {
                    headers: {Authorization: `Bearer ${token}`}
                })
                    .then((response) => {
                        setCart(response.data);
                    });
            } catch (e) {
                setError(`Failed to fetch cart: ${e.message}`);
                localStorage.removeItem("token");
            }

        }

    }, []);

    function isEmpty(cart) {
        return cart.length === 0;
    }

    function increaseQuantity(itemId) {
        setCart(prevCart => ({
            ...prevCart,
            items: prevCart.items.map(item =>
                item.id === itemId
                    ? {
                        ...item,
                        quantity: item.quantity + 1,
                        price: item.product.price * (item.quantity + 1)
                    }
                    : item
            )
        }));
    }

    return (
        <div className="shopping-cart-container">
            {/* LEFT: Cart */}
            <div className="cart">
                <h2>Shopping Cart</h2>

                {isEmpty(cart) ? <></> : (
                    cart.items.map((item) => (
                        <div className="cart-item" key={item.id}>
                            <img src={item.product.imageUrl} alt="" />

                            <div className="details">
                                <h4>{item.product.name}</h4>
                                <p>#{item.product.sku}</p>
                            </div>

                            <div className="quantity">
                                <button>-</button>
                                <span>{item.quantity}</span>
                                <button onClick={() => increaseQuantity(item.id)}>+</button>
                            </div>

                            <div className="price">${item.product.price}</div>

                            <button className="remove">×</button>
                        </div>
                    ))
                )}
            </div>

            {/* RIGHT: Summary */}
            <div className="summary">
                <h3>Order Summary</h3>

                <label>Discount code / Promo code</label>
                <input placeholder="Code" />

                <label>Your bonus card number</label>
                <div className="card-input">
                    <input placeholder="Enter Card Number" />
                    <button>Apply</button>
                </div>

                <div className="totals">
                    <div className="total">
                        <span>Total</span>
                        <span>${cart.totalPrice}</span>
                    </div>
                </div>

                <button className="checkout">Checkout</button>
            </div>
        </div>
    )
}

export default CheckoutPage
