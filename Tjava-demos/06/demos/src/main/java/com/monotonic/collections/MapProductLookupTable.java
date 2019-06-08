package com.monotonic.collections;




public class MapProductLookupTable implements ProductLookupTable
{
    private final Map<Integer, Product> idToProduct = new HashMap<>();

    @Override
    public void addProduct(final Product productToAdd)
    {
        final int id = productToAdd.getId();
        if (idToProduct.containsKey(id))
        {
            throw new IllegalArgumentException(
                "Unable to add product, duplicate id for: " + productToAdd);
        }

        idToProduct.put(id, productToAdd);
    }

    @Override
    public Product lookupById(final int id)
    {
        return idToProduct.get(id);
    }

    public void clear()
    {
        idToProduct.clear();
    }
}
