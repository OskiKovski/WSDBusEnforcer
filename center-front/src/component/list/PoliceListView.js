import ListView from "./ListView";

class PoliceListView extends ListView {

    determineTitleByType = () => {
        return 'Jednostki policji';
    };
}

export default PoliceListView;
