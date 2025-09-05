const postLayout = (() => {
    const showList = (postsCriteria) => {
        const postContainer = document.querySelector("#post-container tbody");
        let text = ``;
        postsCriteria.forEach((post) => {
            text += `
                <tr>
                    <td>${post.id}</td>
                    <td><a href="/post/${post.id}">${post.postTitle}</a></td>
                    <td>${post.postReadCount}</td>
                    <td>${post.relateiveDate}</td>
                </tr>
            `;
        });
        postContainer.innerHTML = text;
    }
    return {showList: showList};

})();