import axios from "axios";
import {useEffect, useState} from "react";
import ProductCard from "../components/ProductCard.jsx";

function HomePage() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/products")
            .then((response) => {
                setProducts(response.data);
            });
    }, []);

    return (
        <div className="page">
            <div className="home-hero">
                <h1 className="home-title">Welcome to my shop</h1>
                <p className="home-subtitle">Discover amazing products at great prices</p>
            </div>
            <div className="container">
                <h2 className="page-title">Our Products</h2>
                <div className="product-grid">
                    {products.map((product) => (
                        <ProductCard key={product.id} product={product} />
                    ))}
                </div>
            </div>
        </div>
    )
}

export default HomePage
