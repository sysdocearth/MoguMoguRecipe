/*
 * レシピ検索スクリプト
 */
$(function(){
	var recipeArray = [];
	$.ajax({
		url: "/recipe/recipeSearch",
		dataType: "json",
		type: 'get'
	}).done(function(data) {
			
		recipeArray = $.map(data, function(item) {
            return {
                label: item.label,
                id: item.key,
                value: item.value
                };
        	});
			$('#recipeautocomplete').autocomplete({
			    lookup:recipeArray,
			    onSelect: function (suggestion) {
			      	document.recipesearch.key.value=suggestion.id;
				    document.recipesearch.submit();
			    }
		})
	})
});
