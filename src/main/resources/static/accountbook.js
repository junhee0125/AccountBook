console.log('Hello World!');

//저장
function save(){
console.log('save')
console.log(document.querySelector('.acontent').value);
console.log(document.querySelector('.acost').value);
    $.ajax({
        url: '/accountbook',
        type: 'POST',
        async: false,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            acontent : document.querySelector('.acontent').value,
            acost : document.querySelector('.acost').value,
        }),
        success: function(data) {
                document.querySelector('.acontent').value = ''
                document.querySelector('.acost').value = ''

        },
    });
    findAll();
}
findAll();
//리스트출력
function findAll(){
     $.ajax({
            url: '/accountbook',
            type: 'GET',
            async: false,
            data: {},
            success: function(data) {
            console.log(data);
                let html=``;
                data.forEach(r => {
                    html +=`
                        <div class="todo"> <!-- todo 항목 1개 -->
                            <div class="acontent"> ${r.acontent} </div>
                            <div class="acost"> ${r.acost} </div>
                            <div class="etcbtns">
                                <button onclick="openModal(${r.ano})" type="button">수정</button>
                                <button onclick="remove(${r.ano})" type="button">제거하기</button>
                            </div>
                        </div>`
                })
                document.querySelector('.todo_bottom').innerHTML=html;
            },
     });
}

//모달창 열기
function openModal(ano) {

console.log(ano)
    $.ajax({
        url: '/accountbook/findbyno',
        type: 'GET',
        data: {ano : ano},
        success: function(data) {
        console.log(data);
            let html=``;

            document.querySelector('.modalwrap').style.display = 'flex';
                html +=`
                    <div class="modal"> <!--쓰기 구역 -->
                            <input class="ccontent" type="text"  value=${data.acontent}>
                            <input class="ccost" type="text"  value=${data.acost} >
                            <button onclick="update(${data.ano})" type="button"> 수정 </button>
                            <button onclick="closeModal()" type="button"> 취소 </button>
                        </div>`

            document.querySelector('.modalwrap').innerHTML=html;
          },
    });




}

//모달창닫기
function closeModal() {
    document.querySelector('.modalwrap').style.display = 'none';


}
//수정
function update(ano) {

    $.ajax({
            url: '/accountbook',
            method: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                ano : ano,
                acontent: document.querySelector('.ccontent').value,
                acost: document.querySelector('.ccost').value,
            }),
            success: function(data) {
                if(data) {
                    closeModal()
                    findAll();
                }
            },
        });

}

//삭제
function remove(ano) {
     $.ajax({
            url: '/accountbook',
            method: 'delete',
            data: { ano : ano },
            success: function(data) {
                if(data)findAll();
            },
        });
}