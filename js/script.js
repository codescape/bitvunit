$(document).ready(function(){
  // specific changelog requested?
  var fragment = $.url().attr('fragment');
    
  // wrap each list in a div
  $('h3.release + ul').wrap('<div class="changelog" />');

  // hide each div and show the first or the requested
  $('.changelog').hide();
  if (!fragment) {
    $('.changelog').first().show();
  } else {
    $("#" + fragment).next('.changelog').show();
  }
    
  // add functionality to toggle the visibility
  $('h3.release')
    .css('cursor', 'pointer')
    .click(function() { $(this).next('.changelog').slideToggle(); })
    .attr('title', function() { return 'Toggle visibility of details for ' + $(this).text(); });
});
