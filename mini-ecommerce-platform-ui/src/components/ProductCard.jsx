import {Link} from "react-router-dom";

function ProductCard({ product}) {
    return (
        <div className="product-card" key={product.id}>
            <img className="product-card-image" src="/no-image.png" alt="no-image" />
            <div className="product-card-content">
                <h3 className="product-card-name">{product.name}</h3>
                <p className="product-card-price">${product.price}</p>
                <div className="product-card-actions">
                    <Link className="btn btn-primary">
                        View Details
                    </Link>
                    <button className="btn btn-secondary">
                        Add to Cart
                    </button>
                </div>
            </div>
        </div>
    )
}

export default ProductCard
