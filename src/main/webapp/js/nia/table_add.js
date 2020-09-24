$(function(){
    // 이 부분은 cloneBTN 클래스가 부여된 요소에 이벤트를 부여하며, 마찬가지로 복사를 수행합니다.
    $(".cloneBTN").on("click", function() {
        $(".listBody tbody").append($(".listTemplate tr").clone(true));
    });
   
    // 이 부분은 삭제를 클릭할때 동작하는 기능을 부여합니다.
    $(".removeBTN").on("click", function() {
        $("tr").has(this).remove();
    });

    // 이 부분은 페이지 로딩이 끝나면 우선 한줄 카피해 두기 위한 것입니다.
    // 다만, 첫 한줄은 삭제를 방지하기 위해, 첫 행 추가가 완료되면, 삭제버튼을 지웁니다.    
    $.when(
        $(".listBody tbody").append($(".listTemplate tr").clone(true))
    ).then(function(){
        $(".listBody .removeBTN").remove();
    });
});