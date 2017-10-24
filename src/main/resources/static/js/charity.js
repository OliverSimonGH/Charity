
function addProductLine(item, containerId) {

    console.log('Adding line to ', containerId);


    var container = $(containerId),
        itemTemplate = $('#charity-detail-template .charity-detail-div');

    console.log('The container is ', container);


    console.log('Adding id=%s, name=%s, number=%s',
        item.charityID, item.charityName, item.charityName);

    var newItem = itemTemplate.clone().appendTo(container);
    newItem.attr('data-id', "charity-" + item.charityID);
    newItem.find('.charity-id').text(item.charityID);
    newItem.find('.charity-name').text(item.charityName);
    newItem.find('.charity-number').text(item.charityNumber);
}

/*
 add all the products to the container div
 */

function setProducts(products, containerId) {
    console.log('Adding to ', containerId);
    var container = $(containerId);
    container.empty();
    products.forEach(function (product) {
        addProductLine(product, containerId)
    });
}

